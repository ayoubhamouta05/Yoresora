package com.youppix.ecommercecourse.presentation.home_app.details.customSize.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Composable
fun CustomSizeTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    isError: Boolean = false,
    errorMessage: String = "",
    readOnly: Boolean = false,
    imeAction: ImeAction = ImeAction.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = SmallPadding),
        shape = RoundedCornerShape(MediumPadding),
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        label = {
            Text(
                text = label, style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = ExtraSmallPadding2.plus(2.dp)),
                textAlign = TextAlign.Center
            )
        },
        leadingIcon = {
            Text(
                text = stringResource(id = R.string.cm),
                style = MaterialTheme.typography.labelSmall
            )
        },
        isError = isError,
        supportingText =
        if (isError) {{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }}
        else null,
        keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
        readOnly = readOnly
    )
}