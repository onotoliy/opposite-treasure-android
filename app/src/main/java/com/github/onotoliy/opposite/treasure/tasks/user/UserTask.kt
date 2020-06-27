package com.github.onotoliy.opposite.treasure.tasks.user

import android.accounts.AccountManager
import com.fasterxml.jackson.core.type.TypeReference
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.treasure.tasks.GetRequest

class UserTask(manager: AccountManager): GetRequest<Option>(
    link = "http://185.12.95.242/api/treasure/v1/user/current",
    manager = manager,
    valueTypeRef = object : TypeReference<Option>() {}
)