package br.com.felipepalm14.eventour.ui.event

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import br.com.felipepalm14.eventour.R
import br.com.felipepalm14.eventour.databinding.FragmentEventDetailBinding
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.domain.network.Result
import br.com.felipepalm14.eventour.domain.network.dto.UserCheckinDTO
import br.com.felipepalm14.eventour.extensions.isEmailValid
import br.com.felipepalm14.eventour.ui.base.BaseFragment
import br.com.felipepalm14.eventour.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import timber.log.Timber


class EventDetailFragment : BaseFragment<FragmentEventDetailBinding, MainViewModel>() {

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_event_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this
        binding.vm = viewModel

        val event = arguments?.getSerializable("event") as Event
        binding.event = event
        viewModel.findEvent(event.id)
        setupListener()
        subscribeUi(binding)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupListener(){
        binding.fabEventLocation.setOnClickListener {
            binding.event?.let { event ->
                val geo = "geo:${event.latitude},${event.longitude}?q=${event.latitude},${event.longitude}(Evento ${event.title})"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geo))
                startActivity(intent)
            }
        }

        binding.fabCheckin.setOnClickListener {
            showCheckindialog(it!!)
        }
    }

    private fun subscribeUi(binding: FragmentEventDetailBinding) {
        viewModel.event.observe(viewLifecycleOwner, Observer { event ->
            Timber.d(event.title)
            binding.event = event
        })
    }

    @Suppress("DEPRECATION")
    private fun showCheckindialog(view: View){
        val builder :AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val content = inflater.inflate(R.layout.dialog_checkin, null)
        builder.setView(content)
            .setPositiveButton(getString(R.string.text_dialog_checkin), null)
            .setNegativeButton(getString(R.string.text_dialog_cancel)) { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setOnShowListener {
            val b: Button =
                dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            b.setOnClickListener {
                val eventId = binding.event!!.id
                val editName = content.findViewById<TextInputEditText>(R.id.name)
                val name = editName.text.toString()

                val editEmail = content.findViewById<TextInputEditText>(R.id.email)
                val email = editEmail.text.toString()

                if (name.isEmpty()){
                    editName.error = getString(R.string.text_type_value)
                }else if(!email.isEmailValid()) {
                    editEmail.error = getString(R.string.text_type_email)
                }else{
                    coroutineScope.launch {
                        viewModel.checkin(UserCheckinDTO(eventId.toString(), name, email)).apply {
                            when (this) {
                                is Result.Success -> {
                                    Timber.d("CODE: ${data.code()}")
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                        binding.fabCheckin.backgroundTintList = ColorStateList.valueOf(requireContext().getColor(R.color.colorPrimaryDark))
                                    else
                                        binding.fabCheckin.backgroundTintList = ColorStateList.valueOf(requireContext().resources.getColor(R.color.colorPrimaryDark))
                                    showSnackBar(getString(R.string.text_success_checkin))
                                }
                                is Result.Failure -> {
                                    Timber.d("Error: $error")
                                    showSnackBar(getString(R.string.text_failed_checkin))
                                }
                            }
                        }
                        dialog.dismiss()
                    }
                }
            }
        }
        dialog.show()
    }

}