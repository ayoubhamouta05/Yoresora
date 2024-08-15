package com.youppix.ecommercecourse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIcon
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title : String , isArabic : Boolean , onBackClicked : ()-> Unit) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
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
            ) {
                onBackClicked()
            }
        }
    )
}