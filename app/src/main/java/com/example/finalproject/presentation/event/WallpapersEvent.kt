package com.example.finalproject.presentation.event

sealed interface WallpapersEvent {
    data object FetchDefaultDataEvent : WallpapersEvent
}