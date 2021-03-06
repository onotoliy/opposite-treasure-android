package com.github.onotoliy.opposite.treasure.ui

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.tooling.preview.Preview
import com.github.onotoliy.opposite.treasure.Screen
import com.github.onotoliy.opposite.treasure.repositories.DepositRepository

@Preview
@Composable
fun DepositPageScreenPreview() {
    DepositPageScreen() {

    }
}

@Composable
fun DepositPageScreen(navigateTo: (Screen) -> Unit = {}) {
    val page = state(init = { DepositRepository.getAll() })

    VerticalScroller {
        Column {
            page.value.context.forEach {
                Line(it.person.name, it.deposit) {
                    navigateTo(Screen.DepositUI(it.uuid))
                }
            }
        }
    }
}
