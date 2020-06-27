package com.github.onotoliy.opposite.treasure.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.*
import androidx.ui.res.stringResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import com.github.onotoliy.opposite.treasure.R
import com.github.onotoliy.opposite.treasure.Screen

@Preview
@Composable
fun MenuPreview() {
    Menu(
        bodyContent = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        scaffoldState = ScaffoldState(drawerState = DrawerState.Closed)
    )
}

@Composable
fun Menu(
    bodyContent: @Composable (Modifier) -> Unit,
    navigateTo: (Screen) -> Unit = {},
    scaffoldState: ScaffoldState = ScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(icon = {
                        Icon(asset = Icons.Filled.Menu)
                    }, onClick = {
                        scaffoldState.drawerState = DrawerState.Opened
                    })
                }
            )
        },
        drawerContent = {
            LeftMenu()
        },
        bodyContent = bodyContent,
        bottomAppBar = {
            BottomAppBar {
                BottomMenu(navigateTo)
            }
        }
    )
}

@Composable
private fun LeftMenu() {
    Column {
        FlowRow {
            Icon(asset = Icons.Filled.Menu)
            Text(text = "Главная")
        }
        Divider()
        FlowRow {
            Icon(asset = Icons.Filled.Menu)
            Text(text = "Депозиты")
        }
    }
}

@Composable
private fun BottomMenu(navigateTo: (Screen) -> Unit = {}) {
    FlowRow(
        mainAxisSize = SizeMode.Expand
    ) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Start
        ) {
            IconButton(
                onClick = { navigateTo(Screen.HomeUI) },
                icon = { Icon(asset = Icons.Filled.Home) }
            )
            IconButton(
                onClick = { navigateTo(Screen.DepositPageUI) },
                icon = { Icon(asset = Icons.Filled.Person) }
            )
        }

        FlowRow(
            mainAxisAlignment = MainAxisAlignment.End
        ) {
            IconButton(
                onClick = {},
                icon = { Icon(asset = Icons.Filled.List) }
            )
            IconButton(
                onClick = {},
                icon = { Icon(asset = Icons.Filled.Info) }
            )
        }
    }
}