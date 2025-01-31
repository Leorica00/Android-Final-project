package com.example.finalproject.data.repository

import com.example.finalproject.data.common.response_handler.HandleAuthResponse
import com.example.finalproject.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val handleAuthResponse: HandleAuthResponse
): AuthRepository {
    override val currentUser get() = auth.currentUser

    override fun getUser(): FirebaseUser? {
        return auth.currentUser
    }
}