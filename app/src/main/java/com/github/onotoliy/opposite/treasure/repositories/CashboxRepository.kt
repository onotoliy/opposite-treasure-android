package com.github.onotoliy.opposite.treasure.repositories

import android.accounts.AccountManager
import android.content.Context
import com.github.onotoliy.opposite.data.Cashbox
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.page.Page
import com.github.onotoliy.opposite.treasure.tasks.cashbox.CashboxTask
import com.github.onotoliy.opposite.treasure.tasks.deposit.DepositPageTask
import com.github.onotoliy.opposite.treasure.tasks.deposit.DepositTask

object CashboxRepository {
    fun get(): Cashbox = CashboxTask().execute().get()
}
