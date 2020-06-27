package com.github.onotoliy.opposite.treasure.repositories

import android.accounts.AccountManager
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.page.Page
import com.github.onotoliy.opposite.treasure.tasks.deposit.DepositPageTask
import com.github.onotoliy.opposite.treasure.tasks.deposit.DepositTask

object DepositRepository {
    fun get(manager: AccountManager): Deposit =
        DepositTask(manager).execute().get()

    fun get(manager: AccountManager, uuid: String): Deposit =
        DepositTask(manager, uuid).execute().get()

    fun getAll(
        manager: AccountManager,
        offset: Int = 0,
        numberOfRows: Int = 20
    ): Page<Deposit> = DepositPageTask(manager, offset, numberOfRows).execute().get()
}
