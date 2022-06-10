package com.mobifyall.restaurantshiftschedular.repos

import com.mobifyall.restaurantshiftschedular.models.Employee
import com.mobifyall.restaurantshiftschedular.models.EmployeeSchedule
import com.mobifyall.restaurantshiftschedular.models.Schedule

interface SchedulesRepo {
    fun getSchedules(): List<EmployeeSchedule>
    fun addSchedule(id: Int, employee: Employee, schedule: Schedule)
}

class SchedulesRepoImp : SchedulesRepo {

    private val list = mutableListOf<EmployeeSchedule>()

    /**
     * We can change implementation any time because it should not affect caller
     * like add anything here
     */
    override fun getSchedules(): List<EmployeeSchedule> {
        return list
    }

    /**
     * this will add the schedule
     */
    override fun addSchedule(id: Int, employee: Employee, schedule: Schedule) {
        list.add(EmployeeSchedule(id, employee = employee, schedule = schedule))
    }

}
