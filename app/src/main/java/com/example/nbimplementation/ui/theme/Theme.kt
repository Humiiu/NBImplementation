package com.example.nbimplementation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = WhiteText,
    secondary = LightGreyText,
    tertiary = DarkGrey,
    background = CharcoalGrey,
    surface = SurfaceGrey,
    onPrimary = CharcoalGrey,
    onSecondary = WhiteText,
    onTertiary = WhiteText,
    onBackground = WhiteText,
    onSurface = WhiteText
)

@Composable
fun NBImplementationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = AppTypography,
        content = content
    )
}
