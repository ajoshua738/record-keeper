package com.example.recordkeeper

import java.io.Serializable



data class ScreenData(
    val record: String,
    val sharedPreferencesName: String,
    val recordFieldHint: String,
) : Serializable