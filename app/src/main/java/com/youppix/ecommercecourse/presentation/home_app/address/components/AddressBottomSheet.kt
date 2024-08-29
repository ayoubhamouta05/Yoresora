package com.youppix.ecommercecourse.presentation.home_app.address.components

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.presentation.home_app.address.AddressEvent
import com.youppix.ecommercecourse.presentation.home_app.address.AddressState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBottomSheet(
    modifier: Modifier = Modifier,
    isInserting: Boolean,
    userId: Int,
    state: AddressState,
    event: (AddressEvent) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(bottom = MediumPadding)
                .animateContentSize()
        ) {
            if (!isInserting) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = SmallPadding)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                        .size(SocialMediaItemSize)
                        .padding(ExtraSmallPadding2)
                        .clickable {
                            if (state.selectedAddress.addressDefault == 1) {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.cannotDeleteYourMainAddress),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            } else {
                                event(
                                    AddressEvent.DeleteAddress(
                                        addressId = state.selectedAddress.addressId,
                                        userId = userId
                                    )
                                )
                            }
                        },
                    tint = MaterialTheme.colorScheme.error
                )
            }
            UpsertDeleteButtonsRow(inserting = isInserting,
                onConfirmCLick = {
                    event(
                        AddressEvent.UpsertAddress(
                            address = state.selectedAddress.copy(userId = userId)
                        )
                    )
                },
                onCancelClick = {
                    onDismissRequest()
                }
            )
        }


    }

}