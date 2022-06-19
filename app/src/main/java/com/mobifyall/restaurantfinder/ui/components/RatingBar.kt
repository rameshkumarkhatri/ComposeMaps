package com.mobifyall.restaurantfinder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.mobifyall.restaurantfinder.TAG_RATING_BAR
import com.mobifyall.restaurantfinder.R
import com.mobifyall.restaurantfinder.ui.theme.Typography

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier,
    count: String
) {
    RatingBarLayout(
        starSize = 20f,
        rating = rating,
        modifier = modifier,
        ratingCountScope = {
            Text(
                text = count,
                modifier = Modifier.padding(start = 6.dp),
                color = Black,
                style = Typography.caption
            )
        }
    )
}

@Composable
fun RatingBarLayout(
    starSize: Float,
    rating: Float,
    modifier: Modifier,
    ratingCountScope: @Composable (Modifier) -> Unit = {},
    onRatingSelected: (rating: Int) -> Unit = {}
) {
    val gapSize = 2.5
    val totalWidth = (5 * starSize) + (4 * gapSize)
    val starHeight = starSize - 2

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .height(starHeight.dp)
                .width(totalWidth.dp)

        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(starHeight.dp),
                color = Yellow,
                backgroundColor = White,
                progress = rating / 5
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 1..5) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(starHeight.dp)
                            .width(starSize.dp)
                            .semantics {
                                testTag = String.format(TAG_RATING_BAR, i)
                                contentDescription = String.format(TAG_RATING_BAR, i)
                            }
                            .clickable {
                                onRatingSelected(i)
                            },
                    )
                    if (i < 5)
                        Box(
                            modifier = Modifier
                                .background(White)
                                .width(gapSize.dp)
                                .fillMaxHeight()
                        )
                }
            }
        }
        ratingCountScope(Modifier)
    }
}
