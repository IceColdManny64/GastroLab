package com.example.gastrolab.utils


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.gastrolab.R

object NotificationHelper {
    private const val CHANNEL_ID = "favorites_channel"
    private const val NOTIFICATION_ID = 1001

    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Recetas Favoritas",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificaciones cuando agregas recetas a favoritos"
            }
            ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            )?.createNotificationChannel(channel)
        }
    }

    fun showAddedToFavoritesNotification(context: Context, recipeTitle: String, ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.favorito)
            .setContentTitle("¡Receta guardada!")
            .setContentText("'$recipeTitle' fue agregada a tus favoritos")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        )?.notify(NOTIFICATION_ID, notification)
    }
}