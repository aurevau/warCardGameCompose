package com.example.warcardgamecompose.ui.theme

import android.graphics.fonts.SystemFonts
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.unit.sp
import com.example.warcardgamecompose.R

// Set of Material typography styles to start with

val ShootingStar = FontFamily(
    listOf(
        Font(resId = R.font.shooting_star, weight = FontWeight.Bold))
)

val Aptos = FontFamily(
    listOf(
        Font(resId = R.font.aptos, weight = FontWeight.Normal))
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ShootingStar,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Aptos,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )






    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)