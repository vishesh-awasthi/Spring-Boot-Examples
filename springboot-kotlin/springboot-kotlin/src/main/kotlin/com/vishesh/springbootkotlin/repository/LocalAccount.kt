package com.vishesh.springbootkotlin.repository

import com.vishesh.springbootkotlin.datasource.AccountSource
import com.vishesh.springbootkotlin.domain.Account
import com.vishesh.springbootkotlin.domain.CurrencyCode
import org.springframework.stereotype.Repository

@Repository
class LocalAccount : AccountSource {

    private val account1 = Account(3143112323, "Vishesh Awasthi", CurrencyCode.RUPEE)
    private val account2 = Account(5425324234, "Ying Yang", CurrencyCode.YEN)
    private val account3 = Account(5432532134, "Mathew Lyod", CurrencyCode.DOLLAR)

    override fun retrieveAccounts(): Collection<Account> = listOf(account1, account2, account3)
}