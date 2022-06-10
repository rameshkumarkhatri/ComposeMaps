package com.mobifyall.restaurantshiftschedular.viewmodels

import android.graphics.drawable.Drawable
import androidx.lifecycle.*
import com.mobifyall.restaurantshiftschedular.models.EmployeeSchedule
import com.mobifyall.restaurantshiftschedular.models.ScheduleData
import com.mobifyall.restaurantshiftschedular.repos.SchedulesRepo
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: SchedulesRepo,
    private val colorDrawables: Map<Int, Drawable>
) : ViewModel() {
    //region private properties
    private val schedulesMLD: MutableLiveData<List<ScheduleData>> =
        MutableLiveData<List<ScheduleData>>()

    //endregion
    init {
//        refreshSchedules()
    }

    //region public accessor for mutable live data
    fun getScheduleLD(): LiveData<List<ScheduleData>> = schedulesMLD
    //endregion

    fun refreshSchedules() {
        viewModelScope.launch {
            val scheduleData = repo.getSchedules()
            schedulesMLD.value = scheduleData.map { emSch ->
                val dateArray = emSch.schedule.startDate
                ScheduleData(
                    details = "${emSch.employee.name} (${emSch.schedule.role}) ${dateArray.time}",
                    drawable = colorDrawables[emSch.schedule.color],
                    time = "22:00"
                )
            }

            updateDataForTotal(scheduleData)
        }
    }

    private fun updateDataForTotal(scheduleData: List<EmployeeSchedule>) {

    }

    class HomeViewModelFactory(
        private val repo: SchedulesRepo,
        private val colorDrawables: Map<Int, Drawable>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(repo, colorDrawables) as T
            }
            throw IllegalArgumentException("Can not create HomeViewModel ")
        }

    }
}