package com.example.finalproject.di

import com.example.finalproject.domain.repository.AuthRepository
import com.example.finalproject.domain.usecase.sign_in.SignInWithEmailAndPasswordUseCase
import com.example.finalproject.domain.usecase.sign_up.SignUpWithEmailAndPasswordUseCase
import com.example.finalproject.domain.usecase.validation.EmailValidatorUseCase
import com.example.finalproject.domain.usecase.validation.FullNameValidationUseCase
import com.example.finalproject.domain.usecase.validation.PasswordValidatorUseCase
import com.example.finalproject.domain.usecase.validation.RepeatPasswordValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideFullNameValidationUseCase(): FullNameValidationUseCase {
        return FullNameValidationUseCase()
    }

    @Singleton
    @Provides
    fun provideEmailValidatorUseCase(): EmailValidatorUseCase {
        return EmailValidatorUseCase()
    }

    @Singleton
    @Provides
    fun providePasswordValidatorUseCase(): PasswordValidatorUseCase {
        return PasswordValidatorUseCase()
    }

    @Singleton
    @Provides
    fun provideRepeatPasswordValidatorUseCase(): RepeatPasswordValidatorUseCase {
        return RepeatPasswordValidatorUseCase()
    }

    @Singleton
    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository): SignInWithEmailAndPasswordUseCase {
        return SignInWithEmailAndPasswordUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpWithEmailAndPasswordUseCase {
        return SignUpWithEmailAndPasswordUseCase(authRepository)
    }
}