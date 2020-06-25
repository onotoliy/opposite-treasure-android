package com.github.onotoliy.opposite.treasure.ui

import androidx.compose.Composable
import com.github.onotoliy.opposite.treasure.repositories.CashboxRepository
import com.github.onotoliy.opposite.treasure.repositories.DepositRepository

@Composable
fun HomeScreen() {
    deposit(
        deposit = DepositRepository.get(),
        cashbox = CashboxRepository.get()
    )
}
