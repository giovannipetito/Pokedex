package com.satispay.pokedex.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.ui.items.ShimmerItem
import com.satispay.pokedex.ui.theme.LocalHubColors
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object Globals {

    val colorList: List<Color> = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta
    )

    @Composable
    fun getBrushLoginColors(): List<Color> {
        return listOf(Color.Magenta, Color.Cyan)
    }

    @Composable
    fun getMainBackgroundColors(): Brush {
        val mainBackgroundColors = listOf(
            // MaterialTheme.colorScheme.surface,
            // MaterialTheme.colorScheme.surfaceVariant,
            LocalHubColors.current.backgroundStartColor,
            LocalHubColors.current.backgroundEndColor
        )
        return Brush.verticalGradient(colors = mainBackgroundColors)
    }

    val LazyListState.isScrolled: Boolean
        get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

    fun formatTime(seconds: String, minutes: String, hours: String): String {
        return "$hours:$minutes:$seconds"
    }

    fun Int.pad(): String {
        return this.toString().padStart(2, '0')
    }

    @Composable
    fun getCurrentRoute(navController: NavHostController): String? {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

    // List of main routes where the drawer and the FAB should be visible.
    val mainRoutes = listOf(
        MainRoutes.Home.route,
        MainRoutes.Profile.route,
        MainRoutes.Settings.route
    )

    fun parseUriString(uriString: String): Uri {
        return Uri.parse(uriString)
    }

    fun decodeUriToBitmap(context: Context, uri: Uri): Bitmap? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                inputStream.use {
                    BitmapFactory.decodeStream(inputStream)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getUriFromBitmap(context: Context, bitmap: Bitmap, fileName: String): Uri? {
        val file = File(context.filesDir, fileName)
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return Uri.fromFile(file)
    }

    @Composable
    fun getTransitionColor(durationMillis: Int = 5000): Color {
        val transition = rememberInfiniteTransition(label = "transition")
        val transitionColor: Color by transition.animateColor(
            initialValue = Color.Magenta,
            targetValue = Color.Cyan,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "login button color"
        )
        return transitionColor
    }

    @Composable
    fun getContentPadding(paddingValues: PaddingValues): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
            end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding()
        )
    }

    @Composable
    fun getExtraContentPadding(paddingValues: PaddingValues, extraPadding: Dp): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr),
            end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr),
            bottom = paddingValues.calculateBottomPadding() + extraPadding
        )
    }

    @Composable
    fun getFloatingActionButtonPadding(paddingValues: PaddingValues): PaddingValues {
        return PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) + 16.dp,
            end = paddingValues.calculateEndPadding(layoutDirection = LayoutDirection.Ltr) + 16.dp,
            bottom = paddingValues.calculateBottomPadding() + 12.dp
        )
    }

    @Composable
    fun ShimmerItems() {
        repeat(6) {
            ShimmerItem()
        }
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