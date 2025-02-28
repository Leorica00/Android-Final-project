package com.example.finalproject.presentation.event

sealed interface WallpaperDetailsEvent {
    data class GetData(val id: Long) : WallpaperDetailsEvent
    data class SetUserImageEvent(val url: String): WallpaperDetailsEvent
}