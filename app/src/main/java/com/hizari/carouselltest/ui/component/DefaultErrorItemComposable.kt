package com.hizari.carouselltest.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hizari.carouselltest.R

@Preview
@Composable
fun PreviewDefaultErrorComposable() {
    Column {
        DefaultErrorComposable()
        Spacer(modifier = Modifier.height(16.dp))
        DefaultErrorComposable(message = "Some Error")
        Spacer(modifier = Modifier.height(16.dp))
        DefaultErrorComposable(
            message = "Error....",
            onRetry = {  }
        )
    }
}

@Composable
fun DefaultErrorComposable(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.something_went_wrong),
    messageColor: Color = Color.DarkGray,
    onRetry: (() -> Unit)? = null
) {

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (kbsError, tError) = createRefs()

        onRetry?.let {
            CarousellNewsButton(
                modifier = Modifier
                    .constrainAs(kbsError) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = "Retry",
                onClick = it
            )
        }

        Text(
            color = messageColor,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(tError) {
                    top.linkTo(kbsError.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = message
        )
    }
}