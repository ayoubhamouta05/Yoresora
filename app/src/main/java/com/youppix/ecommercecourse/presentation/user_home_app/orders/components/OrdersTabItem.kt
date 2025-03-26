package com.youppix.ecommercecourse.presentation.user_home_app.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Stable
@Composable
fun RowScope.OrdersTabItem(title: String, selected: Boolean, onClick: () -> Unit) {


    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topEnd = SmallPadding, topStart = SmallPadding))
        .clickable { onClick() }
        .weight(1f)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color =
                if (selected)
                    MaterialTheme.colorScheme.primary else
                    colorResource(id = R.color.body),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(vertical = Dimens.ExtraSmallPadding2)
                .fillMaxSize()
        )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ExtraSmallPadding)
                    .background(
                        if (selected)  MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(topEnd = SmallPadding, topStart = SmallPadding)
                    )
            )

    }


}