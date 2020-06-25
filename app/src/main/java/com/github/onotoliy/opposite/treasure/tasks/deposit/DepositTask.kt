package com.github.onotoliy.opposite.treasure.tasks.deposit

import android.os.AsyncTask
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.onotoliy.opposite.data.Deposit

class DepositTask(): AsyncTask<String, Void, Deposit>() {
    override fun doInBackground(vararg params: String): Deposit {
        val uuid = params[0]

        return ObjectMapper().readValue(
            com.github.onotoliy.opposite.treasure.tasks.get("http://185.12.95.242/api/treasure/v1/deposit/$uuid"),
            Deposit::class.java
        )
    }
}