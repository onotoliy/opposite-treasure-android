package com.github.onotoliy.opposite.treasure.ui

import android.accounts.AccountManager
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.Dp
import com.github.onotoliy.opposite.data.Cashbox
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.treasure.repositories.CashboxRepository
import com.github.onotoliy.opposite.treasure.repositories.DepositRepository

@Composable
fun DepositScreen(manager: AccountManager, deposit: String) {
    deposit(
        DepositRepository.get(manager, deposit),
        CashboxRepository.get(manager)
    )
}

@Composable
fun deposit(deposit: Deposit, cashbox: Cashbox) {
    VerticalScroller {
        Column {
            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Center,
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = Dp(12f),
                mainAxisSpacing = Dp(8f)
            ) {
                Text(
                    text = deposit.person.name,
                    modifier = Modifier.padding(Dp(8f)),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Line(left = "Депозит", right = deposit.deposit)
            Line(left = "В кассе", right = cashbox.deposit)
            Line(left = "Дата изменения", right = cashbox.lastUpdateDate)
        }
    }
}