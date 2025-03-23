package com.example.gastrolab.ui.theme

import android.app.Activity
import android.hardware.lights.Light
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = IkeaBlue,
    secondary = Midnight,
    tertiary = Midnight,
    background = DarkBlue,
    surface = TexasRose100,
    onPrimary = BoneGray,
    onSurface = Midnight,
    onSecondary = TexasRose100,
    onTertiary = Bluey200,
    onBackground = BoneGray
)

private val LightColorScheme = lightColorScheme(
    primary = TexasRose100,
    secondary = LightRed,
    tertiary = Bluesky,
    background = BoneGray,
    surface = DarkBlue,
    onPrimary = BlueGray,
    onSurface = Bluey,
    onSecondary = Whitey,
    onTertiary = TexasRose200,
    onBackground = IkeaBlue


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GastroLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}