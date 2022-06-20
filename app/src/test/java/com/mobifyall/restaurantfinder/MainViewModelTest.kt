package com.mobifyall.restaurantfinder

import com.mobifyall.restaurantfinder.core.repos.RestaurantSearchRepo
import com.mobifyall.restaurantfinder.lists.FavoriteManger
import com.mobifyall.restaurantfinder.mapper.RestaurantUIMapper
import com.mobifyall.restaurantfinder.rules.MainCoroutineRule
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RestaurantSearchViewModel
    private lateinit var repo: RestaurantSearchRepo
    private lateinit var mapper: RestaurantUIMapper
    @Before
    fun setUp() {
        repo = RestaurantSearchRepoFakeImp()
        viewModel = RestaurantSearchViewModel(repo, mapper, FavoriteManger())
    }

    @Test
    fun `test 1 empty`() = runBlocking {
//        Assert.assertEquals(
//
//        )
    }
}