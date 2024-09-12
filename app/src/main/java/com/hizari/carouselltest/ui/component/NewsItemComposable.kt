package com.hizari.carouselltest.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.hizari.carouselltest.ui.theme.DarkGray
import com.hizari.carouselltest.ui.theme.LightGray
import com.hizari.carouselltest.ui.theme.White

@Preview
@Composable
fun PreviewNewItemComposable() {
    NewsItemComposable(
        bannerUrl = "https://www.example.com/banner.jpg",
        title = "Congrats! You are featured on Home page as a recommended seller!, Congrats! You are featured on Home page as a recommended seller!",
        description = "Hi rita12399, thank you for being awesome on Carousell! We want more Carousellers see you, Hi rita12399, thank you for being awesome on Carousell! We want more Carousellers see you",
        timeAgo = "5 hours ago"
    )
}

@Composable
fun NewsItemComposable(
    modifier: Modifier = Modifier,
    bannerUrl: String,
    title: String,
    description: String,
    timeAgo: String
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (image, titleText, descriptionText, timeText) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(bannerUrl),
                contentDescription = "News banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                contentScale = ContentScale.Crop
            )

            Text(text = title,
                style = MaterialTheme.typography.titleMedium,
                color = DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(image.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    width = Dimension.fillToConstraints
                })

            Text(text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = DarkGray,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(descriptionText) {
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(titleText.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                })

            Text(text = timeAgo,
                style = MaterialTheme.typography.bodySmall,
                color = LightGray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(timeText) {
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(descriptionText.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                })
        }
    }
}