package com.satispay.pokedex.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.ui.theme.LocalPokedexColors

object Globals {

    @Composable
    fun getMainBackgroundColors(): Brush {
        val mainBackgroundColors = listOf(
            // MaterialTheme.colorScheme.surface,
            // MaterialTheme.colorScheme.surfaceVariant,
            LocalPokedexColors.current.backgroundStartColor,
            LocalPokedexColors.current.backgroundEndColor
        )
        return Brush.verticalGradient(colors = mainBackgroundColors)
    }

    @Composable
    fun getCurrentRoute(navController: NavHostController): String? {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

    // List of main routes where the drawer and the FAB should be visible.
    val mainRoutes = listOf(
        MainRoutes.Home.route,
        MainRoutes.Profile.route
    )

    @Composable
    fun getContentPadding(paddingValues: PaddingValues): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
            end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding()
        )
    }

    @Composable
    fun getContentPaddingTop(paddingValues: PaddingValues): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
            end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding(),
            top = paddingValues.calculateTopPadding()
        )
    }

    @Composable
    fun getTextFieldColors(): TextFieldColors {
        return TextFieldColors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            disabledTextColor = MaterialTheme.colorScheme.tertiary,
            errorTextColor = MaterialTheme.colorScheme.error,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            cursorColor = Color.White,
            errorCursorColor = Color.Red,
            textSelectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.onPrimaryContainer,
                backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            focusedIndicatorColor = Color.Transparent, // MaterialTheme.colorScheme.primary
            unfocusedIndicatorColor = Color.Transparent, // MaterialTheme.colorScheme.secondary
            disabledIndicatorColor = Color.Transparent, // MaterialTheme.colorScheme.tertiary
            errorIndicatorColor = Color.Transparent, // MaterialTheme.colorScheme.error
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
            disabledLeadingIconColor = MaterialTheme.colorScheme.tertiary,
            errorLeadingIconColor = MaterialTheme.colorScheme.error,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
            disabledTrailingIconColor = MaterialTheme.colorScheme.tertiary,
            errorTrailingIconColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            disabledLabelColor = MaterialTheme.colorScheme.tertiary,
            errorLabelColor = MaterialTheme.colorScheme.error,
            focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
            disabledPlaceholderColor = MaterialTheme.colorScheme.tertiary,
            errorPlaceholderColor = MaterialTheme.colorScheme.error,
            focusedSupportingTextColor = MaterialTheme.colorScheme.primary,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.secondary,
            disabledSupportingTextColor = MaterialTheme.colorScheme.tertiary,
            errorSupportingTextColor = MaterialTheme.colorScheme.error,
            focusedPrefixColor = MaterialTheme.colorScheme.primary,
            unfocusedPrefixColor = MaterialTheme.colorScheme.secondary,
            disabledPrefixColor = MaterialTheme.colorScheme.tertiary,
            errorPrefixColor = MaterialTheme.colorScheme.error,
            focusedSuffixColor = MaterialTheme.colorScheme.primary,
            unfocusedSuffixColor = MaterialTheme.colorScheme.secondary,
            disabledSuffixColor = MaterialTheme.colorScheme.secondary,
            errorSuffixColor = MaterialTheme.colorScheme.error
        )
    }
}