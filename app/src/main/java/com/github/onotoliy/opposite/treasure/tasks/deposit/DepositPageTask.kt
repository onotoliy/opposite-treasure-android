package com.github.onotoliy.opposite.treasure.tasks.deposit

import android.accounts.AccountManager
import android.os.AsyncTask
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.page.Page
import com.github.onotoliy.opposite.treasure.tasks.GetRequest

class DepositPageTask(
    manager: AccountManager,
    offset: Int = 0,
    numberOfRows: Int = 20
) : GetRequest<Page<Deposit>>(
    link = "http://185.12.95.242/api/treasure/v1/deposit?offset=$offset&numberOfRows=$numberOfRows",
    manager = manager,
    valueTypeRef = object : TypeReference<Page<Deposit>>() {}
)
