package com.mobifyall.restaurantshiftschedular.viewmodels

import androidx.lifecycle.*
import com.mobifyall.restaurantshiftschedular.models.*
import com.mobifyall.restaurantshiftschedular.repos.EmployeesRepo
import com.mobifyall.restaurantshiftschedular.repos.SchedulesRepo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddScheduleViewModel(
    private val scheduleRepo: SchedulesRepo,
    private val employeeRepo: EmployeesRepo,
) : ViewModel() {

    //region private properties
    private val employeesMLD: MutableLiveData<List<Employee>> =
        MutableLiveData<List<Employee>>()

    private val colorsMLD: MutableLiveData<List<Colors>> =
        MutableLiveData<List<Colors>>()

    private val rolesMLD: MutableLiveData<List<Role>> =
        MutableLiveData<List<Role>>()

    //endregion

    //region live data accessor
    fun getRolesLD(): LiveData<List<Role>> = rolesMLD
    fun getColorsLD(): LiveData<List<Colors>> = colorsMLD
    fun getEmployeesLD(): LiveData<List<Employee>> = employeesMLD
    //endregion

    init {
        getDataAndPopulateLiveData()
    }

    private fun getDataAndPopulateLiveData() = viewModelScope.launch {
        employeesMLD.postValue(employeeRepo.getEmployees())
        colorsMLD.postValue(HelperData.colorList)
        rolesMLD.postValue(HelperData.rolesList)
    }

    //region operation/service all
    fun addSchedule(
        schedule: Schedule,
        employee: Employee
    ) = liveData<States> {
        // to show loader
        emit(States.Loading("Adding User"))
        scheduleRepo.addSchedule(employee.id, employee, schedule)
        emit(States.Success("Schedule added successfully"))
    }
    //endregion

    class AddScheduleViewModelFactory(
        private val repo: SchedulesRepo,
        private val employeeRepo: EmployeesRepo,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddScheduleViewModel::class.java)) {
                return AddScheduleViewModel(repo, employeeRepo) as T
            }
            throw IllegalArgumentException("Can not create AddScheduleViewModel ")
        }

    }
}