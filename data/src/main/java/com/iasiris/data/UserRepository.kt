package com.iasiris.data

import com.iasiris.core.model.User

class UserRepository {

    private val user = User("a@a.com","12345678", "John Doe")

    fun getUser(): User = user

    fun addUser(user: User) {}
}