package com.github.onotoliy.opposite.treasure.repositories

import android.accounts.AccountManager
import com.github.onotoliy.opposite.data.Cashbox
import com.github.onotoliy.opposite.treasure.tasks.cashbox.CashboxTask

object CashboxRepository {
    fun get(manager: AccountManager): Cashbox = CashboxTask(manager).execute().get()
}
