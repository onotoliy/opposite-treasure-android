package com.github.onotoliy.opposite.treasure

import android.accounts.Account
import com.github.onotoliy.opposite.treasure.auth.ACCOUNT_TYPE

class TreasureAccount(uuid: String, name: String) : Account(name, ACCOUNT_TYPE)