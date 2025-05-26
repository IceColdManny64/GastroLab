package com.example.gastrolab.utils


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.gastrolab.MainActivity
import com.example.gastrolab.R

object NotificationHelper {
    private const val CHANNEL_ID = "favorites_channel"
    private const val NOTIFICATION_ID = 1001

    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Recetas Favoritas",
                NotificationManager.IMPORTANCE_HIGH
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
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("destination", "favoritesScreen")
        }
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.logo)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.favorito)
            .setLargeIcon(largeIcon)
            .setContentTitle("¡Receta guardada!")
            .setContentText("'$recipeTitle' fue agregada a tus favoritos")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}