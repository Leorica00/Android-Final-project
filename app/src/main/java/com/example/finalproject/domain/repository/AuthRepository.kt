package com.example.finalproject.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun getUser(): FirebaseUser?
}