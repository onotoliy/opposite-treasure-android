package com.github.onotoliy.opposite.treasure

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TreasureAuthenticatorService : Service() {

    private lateinit var authenticator: TreasureAccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        authenticator = TreasureAccountAuthenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder = authenticator.iBinder
}