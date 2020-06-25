package com.github.onotoliy.opposite.treasure.tasks

import android.accounts.AccountManager
import android.content.Context
import com.github.onotoliy.opposite.treasure.auth.authToken
import java.io.InputStream
import java.net.URL

fun get(link: String): InputStream {
    val url = URL(link)
    val connection = url.openConnection()
    connection.setRequestProperty("Content-Type", "application/json")
    connection.setRequestProperty("Authorization", "Bearer ${authToken()}")

    return connection.inputStream
}