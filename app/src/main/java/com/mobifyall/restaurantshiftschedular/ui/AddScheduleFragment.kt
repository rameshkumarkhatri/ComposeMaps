package com.mobifyall.restaurantshiftschedular.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mobifyall.restaurantshiftschedular.R
import com.mobifyall.restaurantshiftschedular.databinding.FragmentSecondBinding
import com.mobifyall.restaurantshiftschedular.repos.EmployeesRepo
import com.mobifyall.restaurantshiftschedular.repos.EmployeesRepoImp
import com.mobifyall.restaurantshiftschedular.repos.SchedulesRepo
import com.mobifyall.restaurantshiftschedular.repos.SchedulesRepoImp
import com.mobifyall.restaurantshiftschedular.viewmodels.AddScheduleViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddScheduleFragment : Fragment() {
    private val viewModel by viewModels<AddScheduleViewModel> {
        AddScheduleViewModel.AddScheduleViewModelFactory(
            SchedulesRepoImp(),
            EmployeesRepoImp()
        )
    }
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_HomeFragment)
//        }

        viewModel.getColorsLD().observe(viewLifecycleOwner) {}
        viewModel.getEmployeesLD().observe(viewLifecycleOwner) {}
        viewModel.getRolesLD().observe(viewLifecycleOwner) {}

    }

    private fun addSchedule() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}