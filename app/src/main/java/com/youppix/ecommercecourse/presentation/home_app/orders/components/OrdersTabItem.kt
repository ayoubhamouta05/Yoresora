package com.youppix.ecommercecourse.presentation.home_app.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.common.Dimens

@Composable
fun RowScope.OrdersTabItem (title : String ,selected : Boolean , onClick : () -> Unit){

    Tab(
        selected = selected,
        onClick = {
                  onClick()
        },
        modifier = Modifier
            .background(
                if (selected)
                    MaterialTheme.colorScheme.primary else
                    MaterialTheme.colorScheme.background,
                RoundedCornerShape(Dimens.SmallPadding)
            )
            .weight(1f)
        ,
        selectedContentColor = MaterialTheme.colorScheme.background ,
        unselectedContentColor = MaterialTheme.colorScheme.primary
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = Dimens.ExtraSmallPadding2)
        )
    }
}