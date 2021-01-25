package com.vishesh.springbootkotlin.service

import com.vishesh.springbootkotlin.datasource.AccountSource
import com.vishesh.springbootkotlin.domain.Account
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AccountService(@Qualifier("localAccount") private val accountSource: AccountSource) {

    fun getAllAccounts(): Collection<Account> = accountSource.retrieveAccounts()

}