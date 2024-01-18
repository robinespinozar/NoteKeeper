package com.raerossi.notekeeper.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,
    outline = md_theme_light_outline,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    inverseSurface = md_theme_light_inverseSurface,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_errorContainer,
    onErrorContainer = md_theme_dark_onErrorContainer,
    outline = md_theme_dark_outline,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    inverseSurface = md_theme_dark_inverseSurface,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

val ColorScheme.seed: Color @Composable get() = md_seed
val ColorScheme.background: Color @Composable get() = md_background
val ColorScheme.title: Color @Composable get() = md_title
val ColorScheme.description: Color @Composable get() = md_theme_ref_neutralVariant40
val ColorScheme.disabled_label: Color @Composable get() = md_disabled_label
val ColorScheme.disabled_placeholder: Color @Composable get() = md_disabled_placeholder
val ColorScheme.description_light: Color @Composable get() = md_theme_ref_description
val ColorScheme.description_dark: Color @Composable get() = md_theme_ref_description_darkmode
val ColorScheme.errorColor: Color @Composable get() = md_theme_error_color
val ColorScheme.primary20: Color @Composable get() = md_theme_ref_primary20
val ColorScheme.primary30: Color @Composable get() = md_theme_ref_primary30
val ColorScheme.primary40: Color @Composable get() = md_theme_ref_primary40
val ColorScheme.primary40_alpha40: Color @Composable get() = md_theme_ref_primary40_alpha40
val ColorScheme.primary50: Color @Composable get() = md_theme_ref_primary50
val ColorScheme.primary60: Color @Composable get() = md_theme_ref_primary60
val ColorScheme.primary70: Color @Composable get() = md_theme_ref_primary70
val ColorScheme.neutral95: Color @Composable get() = md_theme_ref_neutral95
val ColorScheme.tertiary95: Color @Composable get() = md_theme_ref_tertiary95
val ColorScheme.neutralVariant30: Color @Composable get() = md_theme_ref_neutralVariant30
val ColorScheme.neutralVariant40: Color @Composable get() = md_theme_ref_neutralVariant40
val ColorScheme.neutralVariant80: Color @Composable get() = md_theme_ref_neutralVariant80
val ColorScheme.neutralVariant90: Color @Composable get() = md_theme_ref_neutralVariant90
val ColorScheme.neutralVariant95: Color @Composable get() = md_theme_ref_neutralVariant95
val ColorScheme.surfaceContainerHigh: Color @Composable get() = md_theme_ref_surfaceContainerHigh
val ColorScheme.surfaceContainerHighest: Color @Composable get() = md_theme_ref_surfaceContainerHighest
val ColorScheme.topBarContainer: Color @Composable get() = md_theme_light_topBarContainer
val ColorScheme.onTopBarContainer: Color @Composable get() = md_theme_light_onTopBarContainer
val ColorScheme.bottomBarContainer: Color @Composable get() = md_theme_light_BottomBarContainer
val ColorScheme.backgroundGradient: Brush @Composable get() = md_background_gradient
val ColorScheme.primaryGradient: Brush @Composable get() = md_primary_gradient
val ColorScheme.secondaryGradient: Brush @Composable get() = md_secondary_gradient

@Composable
fun NoteKeeperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}