package com.vishesh.springbootkotlin.domain

data class Account(val accountNumber: Long, val accountHolderName: String, val defaultCurrency: CurrencyCode) {}