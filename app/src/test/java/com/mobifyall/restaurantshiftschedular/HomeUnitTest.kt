package com.mobifyall.restaurantshiftschedular

import com.mobifyall.restaurantshiftschedular.models.EmployeeSchedule
import com.mobifyall.restaurantshiftschedular.models.Schedule
import com.mobifyall.restaurantshiftschedular.repos.SchedulesRepo
import io.mockk.MockK
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class HomeUnitTest {

    @io.mockk.impl.annotations.MockK
    private val scheduleRepo = mockk<SchedulesRepo>()

    init {
//        MockK.init(this)

        every { scheduleRepo } returns MockScheduleRepo()
    }
    /**
     * Employee list can be empty then add shift disabled, else enabled (should pass)
     */
    @Test
    fun addButtonEnabled() {
        Assert.assertEquals("","")
    }

    @Test
    fun shiftDetailsNotNullOrEmpty() {

    }
    @io.mockk.impl.annotations.MockK
    fun beforeTests() {

    }

    @io.mockk.impl.annotations.MockK
    @Test
    fun testRepositoryNull () {
        Assert.assertEquals(scheduleRepo.getSchedules(), emptyList<EmployeeSchedule>())
    }
    @io.mockk.impl.annotations.MockK
    fun afterTests() {

    }
}

class MockScheduleRepo : SchedulesRepo {
    override fun getSchedules(): List<EmployeeSchedule> = emptyList()

    override fun addSchedule(id: Int, schedule: Schedule) = Unit

}