package com.mobifyall.restaurantfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobifyall.restaurantfinder.NO_RESULTS
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel
import kotlin.text.Typography

@Composable
fun MainListScreen(viewModel: RestaurantSearchViewModel) {
    Scaffold(
        Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                    )
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(1f)
                .background(Color.White),
        ) {
//            val uiState = viewModel.uiStateLandingLD.observeAsState()
            val uiState = remember {
                viewModel.mainUIState
            }
            when (uiState.value) {
                is MainUIState.Loading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .align(Alignment.CenterHorizontally),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .testTag(TAG_PROGRESS)
                                .padding(bottom = 30.dp),
                            color = Color.Blue
                        )
                    }
                }
                is MainUIState.NoResult -> {
                    Text(
                        modifier = Modifier
                            .testTag(NO_RESULTS)
                            .fillMaxSize(1f)
                            .align(Alignment.CenterHorizontally),
                        text = (uiState.value as MainUIState.NoResult).message,
                        style = Typography.headlineMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                is MainUIState.Success -> {
                                 LazyColumn(
                        Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        itemsIndexed((uiState.value as MainUIState.Success).list) { index, item ->
                            RowCurrency(uiState = item) {
                                callback(index)
                            }
                            Divider(
                                Modifier.padding(vertical = 10.dp),
                                color = Color.Black,
                                thickness = 1.dp
                            )
                        }
                    }
                }
                else -> {
                    //no operation
                }
            }

        }
    }
}