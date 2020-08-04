package br.com.felipepalm14.eventour.ui.event

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.felipepalm14.eventour.R
import br.com.felipepalm14.eventour.databinding.FragmentEventListBinding
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.domain.network.Resource
import br.com.felipepalm14.eventour.ui.EventListAdapter
import br.com.felipepalm14.eventour.ui.base.BaseFragment
import br.com.felipepalm14.eventour.ui.viewmodel.MainViewModel
import timber.log.Timber


class EventListFragment : BaseFragment<FragmentEventListBinding, MainViewModel>(){


    private lateinit var adapter: EventListAdapter


    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_event_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this
        binding.vm = viewModel

        adapter = EventListAdapter()
        binding.recyclerView.adapter = adapter

        subscribeUi(binding)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: List<Event>?) {
        val adapter = recyclerView.adapter as EventListAdapter
        adapter.submitList(data)
        adapter.notifyDataSetChanged()
    }

    private fun subscribeUi(dateBinding: FragmentEventListBinding) {
        viewModel.events.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        Timber.d(it.data.toString())
                        setupRecyclerView(dateBinding.recyclerView,it.data)
                    }
                }
                Resource.Status.ERROR ->
                    it.message?.let { message ->
                        showSnackBar(message)
                    }

                Resource.Status.LOADING ->
                    dateBinding.progressBar.visibility = View.VISIBLE
            }

        })
    }
}