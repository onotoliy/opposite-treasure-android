package com.github.onotoliy.opposite.treasure.repositories

import android.accounts.AccountManager
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.treasure.tasks.user.UserRoleTask
import com.github.onotoliy.opposite.treasure.tasks.user.UserTask

object UserRepository {
    fun get(manager: AccountManager): Option = UserTask(manager).execute().get()
    fun getRoles(manager: AccountManager): Set<String> = UserRoleTask(manager).execute().get()
}