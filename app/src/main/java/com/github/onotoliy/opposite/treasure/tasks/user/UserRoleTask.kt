package com.github.onotoliy.opposite.treasure.tasks.user

import android.accounts.AccountManager
import com.fasterxml.jackson.core.type.TypeReference
import com.github.onotoliy.opposite.treasure.tasks.GetRequest

class UserRoleTask(manager: AccountManager): GetRequest<Set<String>>(
    link = "http://185.12.95.242/api/treasure/v1/user/current",
    manager = manager,
    valueTypeRef = object : TypeReference<Set<String>>() {}
)