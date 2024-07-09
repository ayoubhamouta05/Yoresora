package com.youppix.ecommercecourse.presentation.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import java.lang.Error


@Composable
fun ColumnScope.CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    trailingIcon: ImageVector,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean,
    isPassword: Boolean,
    showPassword: Boolean = false,
    onShowPassword: ((Boolean) -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(
                horizontal = Dimens.HorizontalPaddingSignIn
            ),
        value = value,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        shape = RoundedCornerShape(40),
        singleLine = true,
        trailingIcon = {
            Row {
                if (isPassword) {
                    Icon(
                        if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null,
                        Modifier.clickable {
                            onShowPassword?.let {
                                it(!showPassword)
                            }
                        }.padding(horizontal = SmallPadding)
                    )
                }
                Icon(
                    trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = Dimens.MediumPadding)
                )

            }

        },
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        isError = isError,
        visualTransformation =
        if (isPassword && !showPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None
    )
}



