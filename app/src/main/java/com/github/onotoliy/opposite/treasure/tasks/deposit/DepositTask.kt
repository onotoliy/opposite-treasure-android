package com.github.onotoliy.opposite.treasure.tasks.deposit

import android.accounts.AccountManager
import android.os.AsyncTask
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.treasure.auth.getCurrentUser
import com.github.onotoliy.opposite.treasure.tasks.GetRequest
import java.util.*

class DepositTask(manager: AccountManager, uuid: String) : GetRequest<Deposit>(
    link = "http://185.12.95.242/api/treasure/v1/deposit/$uuid",
    manager = manager,
    valueTypeRef = object : TypeReference<Deposit>() {}
) {
    constructor(manager: AccountManager) : this(manager, manager.getCurrentUser())
}
