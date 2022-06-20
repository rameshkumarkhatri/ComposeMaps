package com.mobifyall.restaurantfinder

import com.mobifyall.restaurantfinder.lists.FavoriteManger
import com.mobifyall.restaurantfinder.mapper.RestaurantUIMapper
import com.mobifyall.restaurantfinder.rules.MainCoroutineRule
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    private val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RestaurantSearchViewModel
    private lateinit var repo: RestaurantSearchRepoFakeImp
    private lateinit var mapper: RestaurantUIMapper
    private var manager = FavoriteManger()

    @Before
    fun setUp() {
        mapper = RestaurantUIMapper(manager)
        repo = RestaurantSearchRepoFakeImp(true)
        viewModel = RestaurantSearchViewModel(repo, mapper, manager)
    }

    @Test
    fun `test 1 error UI`() = runBlocking {
        viewModel.getNearByRestaurant(0.0, 0.0)

        Thread.sleep(1000)
        val result = viewModel.uiState.value
        Assert.assertEquals(
            MainUIState.Error::class.java,
            result.javaClass
        )
    }

    @Test
    fun `test 1 No Result UI`() = runBlocking {
        repo.size = 0
        repo.returnError = false
        viewModel.getNearByRestaurant(0.0, 0.0)

        Thread.sleep(1000)
        val result = viewModel.uiState.value
        Assert.assertEquals(
            MainUIState.NoResult::class.java,
            result.javaClass
        )
    }

    @Test
    fun `test 2 Success Result UI`() = runBlocking {
        repo.size = 1
        repo.returnError = false
        viewModel.getNearByRestaurant(0.0, 0.0)

        Thread.sleep(1000)
        val result = viewModel.uiState.value
        Assert.assertEquals(
            MainUIState.Success::class.java,
            result.javaClass
        )
        Assert.assertEquals(1, (result as MainUIState.Success).list.size)
    }
}