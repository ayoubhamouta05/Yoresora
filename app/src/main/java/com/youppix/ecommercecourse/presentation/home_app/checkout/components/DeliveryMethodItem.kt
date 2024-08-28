package com.youppix.ecommercecourse.presentation.home_app.checkout.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Composable
fun DeliveryMethodItem(
    modifier: Modifier = Modifier,
    methodTitle: String,
    methodInfo: String,
    isSelected: Boolean,
    onSelectClick: () -> Unit,
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(SmallPadding))
        .clickable { onSelectClick() }) {

        Icon(
            painter = painterResource(id = R.drawable.ic_box),
            contentDescription = null,
            modifier = Modifier
                .padding(top = Dimens.ExtraSmallPadding)
                .size(Dimens.LargePadding.minus(Dimens.ExtraSmallPadding2)),
            tint = Color.Unspecified

        )

        Column(
            Modifier
                .align(Alignment.Top)
                .weight(1f)
                .padding(start = Dimens.SmallPadding, end = Dimens.SmallPadding)
        ) {
            Text(
                text = methodTitle, style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = methodInfo, style = MaterialTheme.typography.bodySmall.copy(
                    color = colorResource(id = R.color.body)
                ), modifier = Modifier.offset(y = (-6).dp)
            )
        }


        RadioButton(selected = isSelected, onClick = { onSelectClick() })

    }
}