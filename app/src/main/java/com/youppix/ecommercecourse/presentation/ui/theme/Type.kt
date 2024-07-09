package com.youppix.ecommercecourse.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.youppix.ecommercecourse.R


val Cairo = FontFamily(
    Font(R.font.cairo_black,FontWeight.Black),
    Font(R.font.cairo_bold,FontWeight.Bold),
    Font(R.font.cairo_semibold,FontWeight.SemiBold),
    Font(R.font.cairo_regular,FontWeight.Normal),
    Font(R.font.cairo_light,FontWeight.Light),
    Font(R.font.cairo_extralight,FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleSmall = TextStyle(
        textAlign = TextAlign.Center,
        fontFamily = Cairo,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
//    titleMedium = ,
//    titleLarge = ,

    bodyLarge = TextStyle(
        fontFamily = Cairo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
     //Other default text styles to override
    displaySmall = TextStyle(
        fontSize = 20.sp,
        fontFamily = Cairo,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = Cairo,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Cairo,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Cairo,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 13.sp,
        fontFamily = Cairo,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),


)