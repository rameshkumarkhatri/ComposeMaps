package com.mobifyall.restaurantfinder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.mobifyall.restaurantfinder.TAG_ITEM
import com.mobifyall.restaurantfinder.ui.states.RestaurantUIState
import com.mobifyall.restaurantfinder.ui.theme.Typography

@Composable
@Preview
fun RestaurantItemComponentSmallSmallPreview() {
    Column {
        RestaurantItemComponentSmall(
            uiState = RestaurantUIState(
                title = "Restaurant Title - 1 with new data comes in",
                desc = "$10 - $20 Description",
                isFav = false,
                imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
                4.5f,
                "109", 0.0, 0.0
            ),
            modifier = Modifier.width(300.dp)
        ) {}
        RestaurantItemComponentSmall(
            uiState = RestaurantUIState(
                title = "Restaurant Title - 2",
                desc = "$10 - $20 Description",
                isFav = false,
                imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
                4.5f,
                "109", 0.0, 0.0
            ),
            modifier = Modifier.width(300.dp)
        ) {}
        RestaurantItemComponentSmall(
            uiState = RestaurantUIState(
                title = "Restaurant Title - 3",
                desc = "$10 - $20 Description",
                isFav = false,
                imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
                4.5f,
                "109", 0.0, 0.0
            ),
            modifier = Modifier.width(300.dp)
        ) {}
        RestaurantItemComponentSmall(
            uiState = RestaurantUIState(
                title = "Restaurant Title - 4",
                desc = "$10 - $20 Description",
                isFav = false,
                imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
                4.5f,
                "109", 0.0, 0.0
            ),
            modifier = Modifier.width(300.dp)
        ) {}
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantItemComponentSmall(
    uiState: RestaurantUIState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier
        .testTag(TAG_ITEM)
        .clickable { onClick.invoke() }) {
        val (image, title, rating, desc) = createRefs()

        Column(
            Modifier
                .wrapContentSize()
                .constrainAs(image) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }) {

            val imageLoader = LocalImageLoader.current.newBuilder().build()
            CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                val painter = rememberImagePainter(data = uiState.imageUrl,
                    onExecute = { _, _ -> true },
                    builder = {
                        crossfade(true)
                        crossfade(100)
                        scale(Scale.FILL)
                    })
                Image(
                    painter = painter,
                    contentDescription = "Image",
                    modifier = Modifier
                        .padding(16.dp)
                        .width(64.dp)
                        .height(64.dp),
                    contentScale = ContentScale.FillBounds,
                )
            }
        }

        Text(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }, text = uiState.title, style = Typography.body2, color = Color.Black
        )
        RatingBar(
            rating = uiState.rating, modifier = Modifier
                .padding(8.dp)
                .constrainAs(rating) {
                    top.linkTo(title.bottom)
                    start.linkTo(image.end)
                }, count = uiState.ratingCount
        )
        // we can change color based on the price drop/up
        Text(
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(desc) {
                    top.linkTo(rating.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }, text = uiState.desc, style = Typography.caption, color = Color.Black
        )
    }
}
