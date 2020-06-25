package com.github.onotoliy.opposite.treasure.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.layout.FlowRow
import androidx.ui.layout.MainAxisAlignment
import androidx.ui.layout.SizeMode
import androidx.ui.layout.padding
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.Dp

@Composable
fun Line(left: String, right: String, onClick: () -> Unit = {}) {
    Box(modifier = Modifier.clickable(onClick = onClick).padding(Dp(8f))) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Start,
            mainAxisSize = SizeMode.Expand
        ) {
            Text(
                text = left,
                modifier = Modifier.padding(Dp(8f)),
                overflow = TextOverflow.Ellipsis
            )
        }

        FlowRow(
            mainAxisAlignment = MainAxisAlignment.End,
            mainAxisSize = SizeMode.Expand
        ) {
            Text(
                text = right,
                modifier = Modifier.padding(Dp(8f)),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
