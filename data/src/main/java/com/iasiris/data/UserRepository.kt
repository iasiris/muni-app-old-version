package com.iasiris.data

import com.iasiris.core.model.User

class UserRepository {

    private val user = User("a@a.com","12345678", "John Doe", "", "Argentina")

    fun getUserByEmail(email: String): User {
        return user
    }

    fun addUser(user: User) {}
}