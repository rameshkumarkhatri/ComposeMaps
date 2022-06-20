package com.mobifyall.restaurantfinder.mapper

import com.mobifyall.restaurantfinder.DummyData
import com.mobifyall.restaurantfinder.core.models.Response
import com.mobifyall.restaurantfinder.extensions.emptyString
import com.mobifyall.restaurantfinder.lists.FavoriteManger
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.ui.states.ViewType
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RestaurantMapperUITest {
    private lateinit var mapper: RestaurantUIMapper
    private lateinit var response: Response
    private val error = "this is test message"
    @Before
    fun setup() {
        mapper = RestaurantUIMapper(FavoriteManger())
    }

    @Test
    fun `test error`() {
        response = Response(emptyList(), "Error", error, null)
        val data = mapper.getRestaurantsUIState(response, ViewType.ListView)

        Assert.assertTrue(data is MainUIState.Error)
        Assert.assertEquals(error, (data as MainUIState.Error).message)
    }

    @Test
    fun `test no result`() {
        response = Response(emptyList(), "OK", emptyString(), null)
        val data = mapper.getRestaurantsUIState(response, ViewType.ListView)
        Assert.assertTrue(data is MainUIState.NoResult)
    }

    @Test
    fun `test has result`() {
        val data = mapper.getRestaurantsUIState(DummyData.responseSuccess, ViewType.ListView)
        Assert.assertTrue(data is MainUIState.Success)
        Assert.assertEquals(ViewType.ListView, (data as MainUIState.Success).viewType)
        Assert.assertEquals(1, data.list.size)
    }
}