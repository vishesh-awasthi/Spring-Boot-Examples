package com.vishesh.springbootkotlin.controller

import com.vishesh.springbootkotlin.domain.Account
import com.vishesh.springbootkotlin.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountService: AccountService) {

    @GetMapping()
    fun getAllAccounts(): ResponseEntity<Collection<Account>> = ResponseEntity.ok(accountService.getAllAccounts())
}