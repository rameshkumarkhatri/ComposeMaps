package com.mobifyall.restaurantshiftschedular.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mobifyall.restaurantshiftschedular.adapters.EmployeeScheduledAdapter
import com.mobifyall.restaurantshiftschedular.databinding.FragmentFirstBinding
import com.mobifyall.restaurantshiftschedular.hide
import com.mobifyall.restaurantshiftschedular.repos.SchedulesRepoImp
import com.mobifyall.restaurantshiftschedular.viewmodels.HomeViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<HomeViewModel> {
        HomeViewModel.HomeViewModelFactory(
            repo = SchedulesRepoImp(),
            emptyMap()
        )
    }

    private var _binding: FragmentFirstBinding? = null
    private var adapter = EmployeeScheduledAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        _binding?.rvSchedules?.adapter = adapter
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_HomeFragment_to_SecondFragment)
//        }

        viewModel.getScheduleLD().observe(this.viewLifecycleOwner) {
            adapter.updateData(it)
            binding.pb.hide()
        }

        viewModel.refreshSchedules()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}