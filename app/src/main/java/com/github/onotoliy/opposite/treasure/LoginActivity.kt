package com.github.onotoliy.opposite.treasure

import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.github.onotoliy.opposite.treasure.auth.ACCOUNT_TYPE
import com.github.onotoliy.opposite.treasure.ui.LoginScreen

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
                manager.addAccountExplicitly(Account(username, ACCOUNT_TYPE), password, null)

                manager.getAccountsByType(ACCOUNT_TYPE).forEach {
                    println(it.name)
                }

                val myIntent = Intent(this, MainActivity::class.java)
                startActivity(myIntent)
            }
        }
    }
}