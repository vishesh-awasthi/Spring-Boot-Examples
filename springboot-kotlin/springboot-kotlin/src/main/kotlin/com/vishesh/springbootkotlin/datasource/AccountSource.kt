package com.vishesh.springbootkotlin.datasource

import com.vishesh.springbootkotlin.domain.Account

interface AccountSource {
    fun retrieveAccounts(): Collection<Account>
}