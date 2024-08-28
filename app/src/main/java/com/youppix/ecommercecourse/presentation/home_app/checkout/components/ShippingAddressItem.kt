package com.youppix.ecommercecourse.presentation.home_app.checkout.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Composable
fun ShippingAddressItem(
    modifier: Modifier = Modifier,
    addressTitle: String,
    addressInfo: String,
    onChangeCLick: () -> Unit,
) {

    Row(modifier = modifier.fillMaxWidth()) {

        Icon(
            painter = painterResource(id = R.drawable.ic_location), contentDescription = null,
            modifier = Modifier.padding(top = ExtraSmallPadding)
                .size(LargePadding.minus(ExtraSmallPadding2)) ,
            tint = Color.Unspecified

        )

        Column(Modifier.align(Alignment.Top).weight(1f).padding(start = SmallPadding , end = LargePadding)) {
            Text(
                text = addressTitle, style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = addressInfo, style = MaterialTheme.typography.bodySmall.copy(
                    color = colorResource(id = R.color.body)
                ),
                modifier = Modifier.offset(y= (-6).dp) ,
                maxLines = 2 ,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = modifier
                .clip(
                    shape = CircleShape
                )
                .border(
                    width = 0.3.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    shape = CircleShape
                ).clickable { onChangeCLick() }.align(Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(id = R.string.change),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(horizontal = SmallPadding)
            )
        }

    }


}