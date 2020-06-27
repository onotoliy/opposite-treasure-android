package com.github.onotoliy.opposite.treasure.auth

import android.accounts.Account
import android.accounts.AccountManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.IllegalArgumentException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

const val ACCOUNT_TYPE = "com.github.onotoliy.opposite.treasure"
const val UUID_USER = "com.github.onotoliy.opposite.treasure.user.uuid"
const val FULL_USERNAME = "com.github.onotoliy.opposite.treasure.username"

fun String.toBundle(): Bundle {
    val split: List<String> = split(".")
    val bytes: ByteArray = Base64.decode(split[1].toByteArray(StandardCharsets.ISO_8859_1), Base64.URL_SAFE)
    val json: JsonNode = ObjectMapper().readTree(String(bytes))

    return Bundle().apply {
        putString("auth_token", this@toBundle)
        putString("jti", json["jti"].asText())
        putString("exp", json["exp"].asText())
        putString("sub", json["sub"].asText())
        putString("session_state", json["session_state"].asText())
        putStringArray("roles", json["realm_access"]["roles"].map { it.asText() }.toTypedArray())
        putString("name", json["name"].asText())
        putString("preferred_username", json["preferred_username"].asText())
        putString("given_name", json["given_name"].asText())
        putString("family_name", json["family_name"].asText())
    }
}

fun AccountManager.addTreasureAccount(username: String, password: String, userdata: Bundle?) {
    addAccountExplicitly(Account(username, ACCOUNT_TYPE), password, userdata)
}

fun AccountManager.getAuthToken(): String {
    val account = getAccountsByType(ACCOUNT_TYPE)[0]

    return authToken(account.name, getPassword(account)) ?: throw IllegalArgumentException()
}

fun AccountManager.getCurrentUser():  String {
    return getUserData(getAccountsByType(ACCOUNT_TYPE)[0], UUID_USER)
}

fun asyncAuthToken(username: String, password: String): String? {
    return Auth().execute(username, password).get()
}

class Auth : AsyncTask<String, String, String?>() {
    override fun doInBackground(vararg params: String): String? {
        return authToken(params[0], params[1])
    }
}

fun authToken(username: String, password: String): String? {
    val url = URL("http://185.12.95.242/auth/realms/treasure/protocol/openid-connect/token")
    val parameters =
        "client_id=frontend&username=$username&password=$password&grant_type=password".toByteArray()

    val connection = url.openConnection() as HttpURLConnection
    connection.doOutput = true
    connection.instanceFollowRedirects = false
    connection.requestMethod = "POST"
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    connection.setRequestProperty("charset", "utf-8")
    connection.setRequestProperty("Content-Length", parameters.size.toString())
    connection.useCaches = false;
    connection.outputStream.use {
        it.write(parameters)
    }

    if (connection.responseCode == HttpURLConnection.HTTP_OK) {
        val content = String(connection.inputStream.readBytes())
        return ObjectMapper().readTree(content)["access_token"].asText()
    } else {
        return null
    }
}

fun jwt(authToken: String): Bundle {
    val s = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzNnZPTloxNTY3c25lNUp2SlJtcjBQZnhDbmVGajJLVEF2b3dyOUxvd0drIn0." +
            "eyJqdGkiOiI4NDFiNmQ0YS0zZmQ5LTQ2ZGQtODBkNS0yYmZlOGViNWQ2MWIiLCJleHAiOjE1OTMyMzU5MzMsIm5iZiI6MCwiaWF0IjoxNTkzMjM1NjMzLCJpc3MiOiJodHRwOi8vMTg1LjEyLjk1LjI0Mi9hdXRoL3JlYWxtcy90cmVhc3VyZSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI4ZjNhMDk3MS0wNWMzLTRkNzAtOTRjMi1kYWZjMjVjYTVkZTciLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJmcm9udGVuZCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjBhZDYxZTE0LTgzZGItNDM2OS1iNTA4LTFjMWJmMzE5YTRmMyIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiaHR0cDovLzE4NS4xMi45NS4yNDIiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwibWVtYmVyIiwidW1hX2F1dGhvcml6YXRpb24iLCJ0cmVhc3VyZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiLQkNC90LDRgtC-0LvQuNC5INCf0L7RhdGA0LXRgdC90YvQuSIsInByZWZlcnJlZF91c2VybmFtZSI6ImEucG9ocmVzbnlqIiwiZ2l2ZW5fbmFtZSI6ItCQ0L3QsNGC0L7Qu9C40LkiLCJmYW1pbHlfbmFtZSI6ItCf0L7RhdGA0LXRgdC90YvQuSJ9.JTr_nz4ZnvViCIIwfa1RyhmP8089vTUQ_LfQZ7g9sAHCKZtsuI0leig77Lo23dODWdTffGs66IiwM3JYub4rX7DlmmO7m05aKXUJbwz0Ahb6uWzNnN6x-2P2E7OJPd8XsbRLTZ-OPM-Qcjz5CwYomg1xjxXLCgXDt_QPqK-MrFyRwN5WQcRijwyRB1naW4xvedKX2UBw6K6w1ZAEF80g9smFKfjsbdaNGb9IS11pfoe3sAOm3ZT-HVnZ3LzivmrPswBSzOK2tkpLQD-P6Uap24PNy_OvO7SnlaGrvnqS_0VyO5gywxijUdAk4qbkoHMdPytdqvCNToRrkP4lE90mOw"


    return Bundle().apply {

//        "scope": "email profile",
//        "email_verified": false,
//        "name": "Анатолий Похресный",
//        "preferred_username": "a.pohresnyj",
//        "given_name": "Анатолий",
//        "family_name": "Похресный"
    }
//    val values = authToken.split("\\.")
}