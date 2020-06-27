package com.github.onotoliy.opposite.treasure.ui

import android.view.Gravity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.HorizontalAlignmentLine
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp

@Preview
@Composable
fun LL() {
    Column {
        Line(left = "Андрей Андреев", right = "123")

        Line(left = "Анатолий Похресный", right = "123")
    }
}

@Composable
fun Line(left: String, right: String, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp, 0.dp).clickable(onClick = onClick)
    ) {
        Text(
            text = left,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = right,
            style = MaterialTheme.typography.body2
        )
        Divider(modifier = Modifier.padding(5.dp))
    }
}
