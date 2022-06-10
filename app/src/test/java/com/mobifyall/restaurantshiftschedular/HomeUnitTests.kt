package com.mobifyall.restaurantshiftschedular

import com.mobifyall.restaurantshiftschedular.models.Employee
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class HomeUnitTests {


    @Test
    fun userDetailsNotNull() {
        val userDetails = mockk<Employee> {
            every { name } returns ""
            every { gender } returns ""
            every { id } returns -1
        }

        Assert.assertNotEquals(userDetails.id, -1)
        Assert.assertTrue(userDetails.gender == Gender.FEMALE.gender || userDetails.gender == Gender.MALE.gender)
    }


}