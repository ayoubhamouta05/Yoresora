package com.youppix.ecommercecourse.presentation.admin_home_app.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.Item

@Composable
fun BottomBarSection(price: String , modifyItemClickListener : ()->Unit ) {
    Card(
        Modifier.wrapContentSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(topEnd = MediumPadding, topStart = MediumPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(BottomBarHeight.plus(SmallPadding))
                .padding(horizontal = MediumPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.totalPrice),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.text_medium)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = price,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.da),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black, fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = Dimens.ExtraSmallPadding2),
                    )
                }
            }

            Button(modifier = Modifier
                .weight(2f)
                .wrapContentHeight(), onClick = {
                    modifyItemClickListener()
            }) {
                Icon(
                    imageVector = Icons.Filled.Save, contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.padding(horizontal = SmallPadding)
                )

            }
        }
    }
}