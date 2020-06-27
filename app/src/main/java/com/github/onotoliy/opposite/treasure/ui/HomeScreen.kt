package com.github.onotoliy.opposite.treasure.ui

import android.accounts.AccountManager
import androidx.compose.Composable
import com.github.onotoliy.opposite.treasure.repositories.CashboxRepository
import com.github.onotoliy.opposite.treasure.repositories.DepositRepository

@Composable
fun HomeScreen(manager: AccountManager) {
    deposit(
        deposit = DepositRepository.get(manager),
        cashbox = CashboxRepository.get(manager)
    )
}
