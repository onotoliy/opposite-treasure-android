package com.github.onotoliy.opposite.treasure

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.github.onotoliy.opposite.treasure.auth.addTreasureAccount
import com.github.onotoliy.opposite.treasure.auth.asyncAuthToken
import com.github.onotoliy.opposite.treasure.ui.LoginScreen
import java.nio.charset.StandardCharsets

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ACCOUNT_TYPE = "EXTRA_ACCOUNT_TYPE"
        private const val EXTRA_AUTH_TOKEN_TYPE = "EXTRA_AUTH_TOKEN_TYPE"
        private const val EXTRA_IS_NEW_ACCOUNT = "EXTRA_IS_NEW_ACCOUNT"

        fun getIntent(
            context: Context,
            response: AccountAuthenticatorResponse?,
            accountType: String?,
            authTokenType: String?,
            isNewAccount: Boolean
        ) = Intent(context, LoginActivity::class.java).apply {
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
            putExtra(EXTRA_ACCOUNT_TYPE, accountType)
            putExtra(EXTRA_AUTH_TOKEN_TYPE, authTokenType)
            putExtra(EXTRA_IS_NEW_ACCOUNT, isNewAccount)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager = AccountManager.get(applicationContext)

        setContent {
            LoginScreen() { username, password ->

                manager.addTreasureAccount(username, password, asyncAuthToken(username, password)?.let {
                    val split: List<String> = it.split(".")
                    Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
                    Log.d("JWT_DECODED", "Body: " + getJson(split[1]))



                    Bundle()
                })

                val myIntent = Intent(this, MainActivity::class.java)
                startActivity(myIntent)
            }
        }


    }

    fun getJson(strEncoded: String): String {
        val decodedBytes1: ByteArray = Base64.decode(strEncoded.toByteArray(StandardCharsets.ISO_8859_1), Base64.URL_SAFE)
        val decodedBytes2: ByteArray = Base64.decode(strEncoded.toByteArray(StandardCharsets.US_ASCII), Base64.URL_SAFE)
        return String(decodedBytes1)
    }
}