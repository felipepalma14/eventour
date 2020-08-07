package br.com.felipepalm14.eventour.ui.event

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.content.ContextCompat.getColor
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.felipepalm14.eventour.R
import br.com.felipepalm14.eventour.databinding.FragmentEventListBinding
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.ui.EventListAdapter
import br.com.felipepalm14.eventour.ui.base.BaseFragment
import br.com.felipepalm14.eventour.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_event_list.*
import timber.log.Timber


class EventListFragment : BaseFragment<FragmentEventListBinding, MainViewModel>(){


    private lateinit var adapter: EventListAdapter


    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_event_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this
        binding.vm = viewModel

        setSwipeRefresh()
        setObservers()

        adapter = EventListAdapter(EventListAdapter.OnClickListener {
            val bundle = bundleOf("event" to it)
            view.findNavController().navigate(R.id.to_details, bundle)
        })
        binding.recyclerView.adapter = adapter

        subscribeUi(binding)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        viewModel.fetchFromRemote()
        viewModel.fetchFromLocal()
        super.onResume()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: List<Event>?) {
        val adapter = recyclerView.adapter as EventListAdapter
        adapter.submitList(data)
        adapter.notifyDataSetChanged()
        runAnimationAgain()
    }

    private fun setSwipeRefresh() {
        binding.swipe.setColorSchemeColors(getColor(requireContext(), R.color.green))
        binding.swipe.setOnRefreshListener { viewModel.fetchFromRemote() }
    }

    private fun setObservers() {
        viewModel.refreshing.observe(viewLifecycleOwner, Observer {
            binding.swipe.isRefreshing = it
        })
        viewModel.toast.observe(viewLifecycleOwner, Observer { msg ->
            showSnackBar(msg)
        })

    }

    private fun subscribeUi(dateBinding: FragmentEventListBinding) {
        viewModel.events.observe(viewLifecycleOwner, Observer { eventsList ->
                binding.progressBar.visibility = View.GONE
                if (!eventsList.isNullOrEmpty()) {
                    Timber.d(eventsList.toString())
                    setupRecyclerView(dateBinding.recyclerView,eventsList)
                }

        })
    }

    private fun runAnimationAgain() {
        val controller: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_fall_down)
        recyclerView.layoutAnimation = controller
        adapter.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }
}