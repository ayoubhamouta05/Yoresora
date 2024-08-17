package com.youppix.ecommercecourse.presentation.home_app.customSize.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection(
    customSizeName : String , focusRequester : FocusRequester,
    isArabic : Boolean,
    onValueChange : (String) -> Unit,
    navigateBack : ()-> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            BasicTextField(
                value = customSizeName,
                onValueChange = {
                    onValueChange(it)
                },
                textStyle = MaterialTheme.typography.titleSmall,
                singleLine = true,
                modifier = Modifier.focusRequester(focusRequester)
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.SmallPadding)
            .padding(top = Dimens.SmallPadding),
        navigationIcon = {
            CustomIcon(
                modifier = Modifier.rotate(
                    if (isArabic) 180f else 0f
                ),
                imageVector = Icons.Default.ArrowBack
            ) { navigateBack() }
        }
    )
}