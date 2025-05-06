package com.example.gastrolab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LightSalmon,
    secondary = FreshGreen,
    tertiary = CardDark,
    background = BackgroundDark,
    surface = CardDark,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = TextSecondaryDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark
)

private val LightColorScheme = lightColorScheme(
   primary = LightSalmon,
     secondary = FreshGreen,
     tertiary = CardLight,
     background = BackgroundLight,
     surface = CardLight,
     onPrimary = Color.Black,
     onSecondary = Color.White,
     onTertiary = TextSecondaryLight,
     onBackground = TextPrimaryLight,
     onSurface = TextPrimaryLight



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