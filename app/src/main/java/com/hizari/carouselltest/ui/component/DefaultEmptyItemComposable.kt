package com.hizari.carouselltest.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.hizari.carouselltest.R
import com.hizari.carouselltest.ui.theme.DarkGray
import com.hizari.carouselltest.ui.theme.LightGray
import com.hizari.carouselltest.ui.theme.White

@Preview
@Composable
fun PreviewDefaultEmptyComposable() {
    Column(modifier = Modifier.background(White)) {
        DefaultEmptyComposable()
        DefaultEmptyComposable(
            message = "No Data",
            description = "Much Empty"
        )
    }
}

@Composable
fun DefaultEmptyComposable(
    modifier: Modifier = Modifier,
    imageResource: Int = R.drawable.img_empty,
    message: String = LocalContext.current.getString(R.string.empty_data),
    description: String? = null,
    messageColor: Color = DarkGray,
    descriptionColor: Color = LightGray,
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        val (iEmpty, tEmptyMessage, tEmptyDescription) = createRefs()

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = message,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .constrainAs(iEmpty) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.ratio("H, 2.5:3")
                }
        )

        Text(
            color = messageColor,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(tEmptyMessage) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(iEmpty.bottom, 16.dp)
                    width = Dimension.fillToConstraints
                },
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            text = message,
            textAlign = TextAlign.Center,
        )

        if (description.isNullOrEmpty().not()) {
            Text(
                color = descriptionColor,
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(tEmptyDescription) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(tEmptyMessage.bottom, 8.dp)
                        width = Dimension.fillToConstraints
                    },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = description.orEmpty(),
                textAlign = TextAlign.Center
            )
        }
    }
}