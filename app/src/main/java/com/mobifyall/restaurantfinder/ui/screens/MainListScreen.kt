package com.mobifyall.restaurantfinder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobifyall.restaurantfinder.ERROR
import com.mobifyall.restaurantfinder.NO_RESULTS
import com.mobifyall.restaurantfinder.R.*
import com.mobifyall.restaurantfinder.TAG_PROGRESS
import com.mobifyall.restaurantfinder.TOGGLE
import com.mobifyall.restaurantfinder.ui.components.RestaurantItemComponent
import com.mobifyall.restaurantfinder.ui.states.MainUIState
import com.mobifyall.restaurantfinder.ui.states.ViewType
import com.mobifyall.restaurantfinder.ui.theme.Typography
import com.mobifyall.restaurantfinder.viewmodels.RestaurantSearchViewModel

@Composable
fun MainListScreen(viewModel: RestaurantSearchViewModel) {
    Scaffold(
        Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = stringResource(id = string.app_name),
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
            val uiState =
                viewModel.uiState.collectAsState()
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
                        style = Typography.h4,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                is MainUIState.Success -> {
                    val data = (uiState.value as MainUIState.Success)
                    Text(
                        modifier = Modifier
                            .testTag(TOGGLE)
                            .fillMaxWidth(1f)
                            .background(color = Color.Blue)
                            .clickable {
                                viewModel.toggle(data.viewType)
                            },
                        text = data.viewType.text,
                        style = Typography.h5,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    if (data.viewType == ViewType.ListView) {
                        LazyColumn(
                            Modifier.fillMaxSize(0.9f),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            itemsIndexed(data.list) { index, item ->
                                RestaurantItemComponent(uiState = item) {
                                    //todo add functionality
                                }
                                Divider(
                                    Modifier.padding(vertical = 10.dp),
                                    color = Color.Black,
                                    thickness = 1.dp
                                )
                            }
                        }
                    } else {
                        MapsUI(Modifier.fillMaxSize(1f), data, viewModel)
                    }
                }
                else -> {
                    Text(
                        modifier = Modifier
                            .testTag(ERROR)
                            .fillMaxSize(1f)
                            .align(Alignment.CenterHorizontally),
                        text = (uiState.value as MainUIState.Error).message,
                        style = Typography.h4,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}