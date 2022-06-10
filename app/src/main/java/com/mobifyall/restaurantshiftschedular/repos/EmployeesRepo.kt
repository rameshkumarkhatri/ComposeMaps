package com.mobifyall.restaurantshiftschedular.repos

import com.mobifyall.restaurantshiftschedular.Gender
import com.mobifyall.restaurantshiftschedular.models.Employee

interface EmployeesRepo {
    fun getEmployees() : List<Employee>
}

class EmployeesRepoImp : EmployeesRepo{

    private val list = mutableListOf<Employee>().apply {
        add(Employee(1, "David Chang", Gender.MALE.name))
        add(Employee(2, "Alison Creg", Gender.FEMALE.name))
        add(Employee(3, "Kyle Arkansas", Gender.MALE.name))
        add(Employee(4, "Shari Bieber", Gender.FEMALE.name))
    }

    /**
     * We can change implementation any time because it should not affect caller
     * like add anything here
     */
    override fun getEmployees(): List<Employee> {
        return list
    }

}
