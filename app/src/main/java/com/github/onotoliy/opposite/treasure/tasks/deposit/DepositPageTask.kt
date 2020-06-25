package com.github.onotoliy.opposite.treasure.tasks.deposit

import android.os.AsyncTask
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.page.Page

class DepositPageTask(): AsyncTask<String, Void, Page<Deposit>>() {
    override fun doInBackground(vararg params: String): Page<Deposit> {
        val offset = params[0]
        val numberOfRows = params[1]

        return ObjectMapper().readValue(
            com.github.onotoliy.opposite.treasure.tasks.get("http://185.12.95.242/api/treasure/v1/deposit?offset=$offset&numberOfRows=$numberOfRows"),
            object : TypeReference<Page<Deposit>>() {}
        )
    }
}