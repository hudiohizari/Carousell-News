package com.hizari.carouselltest.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hizari.carouselltest.ui.theme.Gray
import com.hizari.carouselltest.ui.theme.Red
import com.hizari.carouselltest.ui.theme.White

@Preview
@Composable
private fun PreviewCarousellNewsButton() {
    Column {
        CarousellNewsButton(
            onClick = {},
            text = "Button Solid Enable",
            isEnabled = true
        )
        CarousellNewsButton(
            onClick = {},
            text = "Button Solid Disable",
            isEnabled = false
        )
        CarousellNewsButton(
            onClick = {},
            composeItem = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.ShoppingCart,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "With Icon",
                        color = White,
                    )
                }
            },
            isEnabled = true
        )
    }
}

@Composable
fun CarousellNewsButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "",
    composeItem: @Composable () -> Unit = {},
    isEnabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Red,
            disabledContentColor = Gray
        ),
        shape = RoundedCornerShape(
            topStart = 64.dp,
            topEnd = 64.dp,
            bottomStart = 64.dp,
            bottomEnd = 64.dp
        )
    ) {
        if (text.isEmpty()) {
            composeItem()
        } else {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = text,
                color = Color.White
            )
        }
    }
}