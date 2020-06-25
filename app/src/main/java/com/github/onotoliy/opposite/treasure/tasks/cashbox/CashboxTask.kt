package com.github.onotoliy.opposite.treasure.tasks.cashbox

import android.accounts.AccountManager
import android.content.Context
import android.os.AsyncTask
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.onotoliy.opposite.data.Cashbox

class CashboxTask(): AsyncTask<Void, Void, Cashbox>() {
    override fun doInBackground(vararg params: Void): Cashbox {
        return ObjectMapper().readValue(
            com.github.onotoliy.opposite.treasure.tasks.get("http://185.12.95.242/api/treasure/v1/cashbox"),
            Cashbox::class.java
        )
    }
}