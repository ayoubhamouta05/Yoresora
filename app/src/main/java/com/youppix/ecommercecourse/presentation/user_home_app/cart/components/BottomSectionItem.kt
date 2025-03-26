package com.youppix.ecommercecourse.presentation.user_home_app.cart.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens

@Composable
fun BottomSectionItem(
    title : String ,
    value : String
) {


    Row(modifier = Modifier.padding(top = Dimens.ExtraSmallPadding2)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = colorResource(id = R.color.body)
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.prixValue,value),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }

}