package com.youppix.ecommercecourse.presentation.components

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun keyboardAsState(): State<Boolean> {
    val context = LocalContext.current
    val isKeyboardOpen = remember { mutableStateOf(false) }

    DisposableEffect(context) {
        val activity = context as? Activity
        val rootView = activity?.window?.decorView
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            rootView?.getWindowVisibleDisplayFrame(r)
            val screenHeight = rootView?.rootView?.height ?: 0
            val keypadHeight = screenHeight - r.bottom
            isKeyboardOpen.value = keypadHeight > screenHeight * 0.15
        }
        rootView?.viewTreeObserver?.addOnGlobalLayoutListener(listener)
        onDispose {
            rootView?.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
        }
    }
    return isKeyboardOpen
}