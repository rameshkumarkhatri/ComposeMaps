package com.mobifyall.restaurantshiftschedular.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobifyall.restaurantshiftschedular.databinding.EmployeScheduleItemBinding
import com.mobifyall.restaurantshiftschedular.models.ScheduleData
import com.mobifyall.restaurantshiftschedular.viewstates.ScheduleItemViewState

class EmployeeScheduledAdapter(var list: MutableList<ScheduleData> = mutableListOf<ScheduleData>()) :
    RecyclerView.Adapter<EmployeeScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeScheduleViewHolder {
        return EmployeeScheduleViewHolder(
            EmployeScheduleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            ).apply {
                viewState = ScheduleItemViewState()
            }
        )
    }

    override fun onBindViewHolder(holder: EmployeeScheduleViewHolder, position: Int) {
        val data = list[position]
        holder.binding.viewState?.updateData(data.details, data.drawable, data.time)
    }

    override fun getItemCount(): Int = list.size
    fun updateData(newList: List<ScheduleData>?) {
        list.clear()
        if (newList != null) {
            list.addAll(newList)
        }
        // we can use it here but in future can change to diff utils
        notifyDataSetChanged()
    }
}

class EmployeeScheduleViewHolder(val binding: EmployeScheduleItemBinding) :
    RecyclerView.ViewHolder(binding.root)
