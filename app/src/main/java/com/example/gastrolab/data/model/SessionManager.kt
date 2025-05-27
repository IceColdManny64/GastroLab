package com.example.gastrolab.data.model

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveSession(userId: Int, email: String) {
        prefs.edit()
            .putInt("user_id", userId)
            .putString("user_email", email)
            .apply()
    }

    fun getUserId(): Int = prefs.getInt("user_id", -1)
    fun getUserEmail(): String = prefs.getString("user_email", "") ?: ""

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
