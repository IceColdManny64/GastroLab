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
    primary = DarkBrown,
    secondary = Coral100,
    tertiary = Greeny,
    background = BlueGray,
    surface = Bittersweet200,
    onPrimary = LightBrown,
    onSurface = GastroPink200,
    primaryContainer = WildWate200,
    onSecondary = Whitey,
    onTertiary = BrightOrange,
    onBackground = GastroPink200
)

private val LightColorScheme = lightColorScheme(
    primary = TexasRose100,
    secondary = DarkBrown,
    tertiary = TexasRose100,
    background = Whitey,
    surface = Bittersweet100,
    onPrimary = Whitey,
    onSurface = DarkBrown,
    primaryContainer = Whitey,
    onSecondary = Salmon,
    onTertiary = BrightOrange,
    onBackground = GastroPink200,
    surfaceVariant = LightBrown


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
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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