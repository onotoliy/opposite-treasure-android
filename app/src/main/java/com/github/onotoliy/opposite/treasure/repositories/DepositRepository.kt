package com.github.onotoliy.opposite.treasure.repositories

import android.accounts.AccountManager
import android.content.Context
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.page.Page
import com.github.onotoliy.opposite.treasure.tasks.deposit.DepositPageTask
import com.github.onotoliy.opposite.treasure.tasks.deposit.DepositTask

object DepositRepository {
    fun get(): Deposit = getAll().context[0]
    fun get(uuid: String): Deposit = DepositTask().execute(uuid).get()
    fun getAll(): Page<Deposit> = DepositPageTask().execute("0", "20").get()
}
