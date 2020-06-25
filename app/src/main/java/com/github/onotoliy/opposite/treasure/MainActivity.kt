package com.github.onotoliy.opposite.treasure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.Surface
import com.github.onotoliy.opposite.treasure.ui.DepositPageScreen
import com.github.onotoliy.opposite.treasure.ui.DepositScreen
import com.github.onotoliy.opposite.treasure.ui.HomeScreen
import com.github.onotoliy.opposite.treasure.ui.Menu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppContent()
        }
    }
}

sealed class Screen {
    object HomeUI : Screen()
    object DepositPageUI : Screen()
    data class DepositUI(val deposit: String) : Screen()
}

@Composable
private fun AppContent() {
    val state = state<Screen> {
        Screen.HomeUI
    }

    val navigateTo: (Screen) -> Unit = {
        state.value = it
    }

    Menu(
        navigateTo = navigateTo,
        bodyContent = {
            Crossfade(state.value) { screen ->
                Surface {
                    when (screen) {
                        is Screen.DepositPageUI -> DepositPageScreen(navigateTo)
                        is Screen.HomeUI -> HomeScreen()
                        is Screen.DepositUI -> DepositScreen(screen.deposit)
                    }
                }
            }
        }
    )
}
