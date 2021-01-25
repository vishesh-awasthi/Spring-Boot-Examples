package com.vishesh.springbootkotlin.repository

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class LocalAccountTest {

    private val localAccount = LocalAccount()

    @Test
    fun retrieveAccounts() {
        val accounts = localAccount.retrieveAccounts()
        assertNotNull(accounts)
    }
}