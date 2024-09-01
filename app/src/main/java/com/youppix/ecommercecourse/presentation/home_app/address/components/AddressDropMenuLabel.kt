package com.youppix.ecommercecourse.presentation.home_app.address.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Composable
fun AddressDropMenuLabel(
    modifier: Modifier = Modifier,
    name: String,
    nameAr: String,
    isArabic: Boolean,
    clickable : Boolean ,
    onChangeClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.SmallPadding)
            .sizeIn(minHeight = 40.dp)
            .clip(RoundedCornerShape(40))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                shape = RoundedCornerShape(40)
            )
            .clickable (enabled = clickable){
                onChangeClick()
            }
            .padding(vertical = SmallPadding.plus(ExtraSmallPadding))
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = if (isArabic) nameAr else name, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f).padding(start = MediumPadding.minus(4.dp))
        )
        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = null ,
            modifier = Modifier.size(LargePadding).padding(ExtraSmallPadding).rotate(
                if(isArabic) 0f else 180f
            )
        )
    }
}