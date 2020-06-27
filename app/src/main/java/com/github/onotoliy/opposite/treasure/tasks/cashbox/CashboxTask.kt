package com.github.onotoliy.opposite.treasure.tasks.cashbox

import android.accounts.AccountManager
import android.content.Context
import android.os.AsyncTask
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.onotoliy.opposite.data.Cashbox
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.page.Page
import com.github.onotoliy.opposite.treasure.tasks.GetRequest

class CashboxTask(manager: AccountManager) : GetRequest<Cashbox>(
    link = "http://185.12.95.242/api/treasure/v1/cashbox",
    manager = manager,
    valueTypeRef = object : TypeReference<Cashbox>() {}
)
