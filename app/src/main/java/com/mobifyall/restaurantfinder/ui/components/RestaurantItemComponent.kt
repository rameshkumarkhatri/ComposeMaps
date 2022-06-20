package com.mobifyall.restaurantfinder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
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
import com.mobifyall.restaurantfinder.ui.theme.Purple
import com.mobifyall.restaurantfinder.ui.theme.Typography
import okhttp3.internal.notify

@Composable
@Preview(widthDp = 400)
fun RestaurantItemComponentPreview() {
    Column {
        RestaurantItemComponent(uiState = RestaurantUIState(
            title = "Restaurant Title - 1",
            desc = "$10 - $20 Description",
            isFav = false,
            imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
            4.5f,
            "109",
            0.0,
            0.0, id = "9"
        ), {}) {}
        RestaurantItemComponent(uiState = RestaurantUIState(
            title = "Restaurant Title - 2",
            desc = "$10 - $20 Description",
            isFav = true,
            imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
            4.5f,
            "109",
            0.0,
            0.0, id = "9"
        ), {}) {}
        RestaurantItemComponent(uiState = RestaurantUIState(
            title = "Restaurant Title - 3",
            desc = "$10 - $20 Description",
            isFav = false,
            imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
            4.5f,
            "109",
            0.0,
            0.0, id = "9"
        ), {}) {}
        RestaurantItemComponent(uiState = RestaurantUIState(
            title = "Restaurant Title - 4",
            desc = "$10 - $20 Description",
            isFav = false,
            imageUrl = "https://i.picsum.photos/id/326/200/300.jpg?hmac=SKzjQ5ycCVyISiOfq2m-GqpQ5zWT_J202KPYG7z0uB4",
            4.5f,
            "109",
            0.0,
            0.0, id = "9"
        ), {}) {}
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantItemComponent(
    uiState: RestaurantUIState, favClicked: () -> Unit, onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TAG_ITEM)
            .clickable { onClick.invoke() }) {
        val (image, title, rating, fav, desc) = createRefs()

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
                }, text = uiState.title, style = Typography.body1, color = Color.Black
        )
        val isFav = remember { mutableStateOf(uiState.isFav) }


        RatingBar(
            rating = uiState.rating, modifier = Modifier
                .padding(8.dp)
                .constrainAs(rating) {
                    top.linkTo(title.bottom)
                    start.linkTo(image.end)
                    width = Dimension.fillToConstraints
                    end.linkTo(parent.end)
                }, count = uiState.ratingCount
        )
        // we can change color based on the price drop/up
        Text(
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(desc) {
                    top.linkTo(rating.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(image.end)
                    width = Dimension.fillToConstraints
                }, text = uiState.desc, style = Typography.body2, color = Color.Black
        )

        IconButton(onClick = {
            isFav.value = !isFav.value
            favClicked()
        }, modifier = Modifier
            .size(50.dp)
            .constrainAs(fav) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)

            }) {
            Icon(
                modifier = Modifier.padding(12.dp),
                tint = Red,
                imageVector = if (isFav.value) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = "Favorite button"
            )
        }
    }
}
