package com.youppix.ecommercecourse.presentation.home_app.address.components

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.presentation.components.CustomTextField
import com.youppix.ecommercecourse.presentation.components.keyboardAsState
import com.youppix.ecommercecourse.presentation.home_app.address.AddressEvent
import com.youppix.ecommercecourse.presentation.home_app.address.AddressState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBottomSheet(
    modifier: Modifier = Modifier,
    isInserting: Boolean,
    userId: Int,
    userCustomerId : String,
    state: AddressState,
    isArabic: Boolean,
    event: (AddressEvent) -> Unit,
    onDismissRequest: () -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val isKeyboardOpen by keyboardAsState()
    val context = LocalContext.current

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }


    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = LargePadding),
        onDismissRequest = { onDismissRequest() },
        containerColor = MaterialTheme.colorScheme.background,
        sheetState = sheetState
    ) {
        Scaffold(modifier =
        modifier
            .fillMaxSize()
            .padding(bottom = if (isKeyboardOpen) 0.dp else LargePadding)
            .padding(bottom = if (isKeyboardOpen) SmallPadding else MediumPadding),
            bottomBar = {
                Column {
                    if (!isInserting) {
                        DeleteIcon(
                            addressDefault = state.selectedAddress.addressDefault,
                            addressId = state.selectedAddress.addressId,
                            userId = userId,
                            event = event
                        )
                    }

                    UpsertCancelButtonsRow(inserting = isInserting,
                        onConfirmCLick = {
                            if(state.selectedAddress.addressDefault == 1){
                                if (state.items.isNotEmpty()){
                                    event(AddressEvent.UpsertMultipleAddress(
                                        address1 = state.items[state.defaultAddressIndex].copy(
                                            addressDefault = 0
                                        ),
                                        address2 = state.selectedAddress.copy(
                                            userId = userId,
                                            addressName = state.selectedAddress.addressName,
                                            addressCommune = state.selectedAddress.addressCommune,
                                            addressWilaya = state.selectedAddress.addressWilaya,
                                            addressCodePostal = state.selectedAddress.addressCodePostal,
                                            addressDefault = state.selectedAddress.addressDefault
                                        ),
                                        checkError = true,
                                        userCustomerId = userCustomerId ,
                                        isArabic = isArabic,
                                        context = context
                                    ))
                                }else{
                                    event(
                                        AddressEvent.UpsertAddress(
                                            address = state.selectedAddress.copy(
                                                userId = userId,
                                                addressName = state.selectedAddress.addressName,
                                                addressCommune = state.selectedAddress.addressCommune,
                                                addressWilaya = state.selectedAddress.addressWilaya,
                                                addressCodePostal = state.selectedAddress.addressCodePostal,
                                                addressDefault = state.selectedAddress.addressDefault
                                            ),
                                            checkError = true,
                                            userCustomerId = userCustomerId,
                                            context = context,
                                            isArabic = isArabic
                                        )
                                    )
                                }
                            }else {
                                event(
                                    AddressEvent.UpsertAddress(
                                        address = state.selectedAddress.copy(
                                            userId = userId,
                                            addressName = state.selectedAddress.addressName,
                                            addressCommune = state.selectedAddress.addressCommune,
                                            addressWilaya = state.selectedAddress.addressWilaya,
                                            addressCodePostal = state.selectedAddress.addressCodePostal,
                                            addressDefault = state.selectedAddress.addressDefault
                                        ),
                                        checkError = true,
                                        userCustomerId = userCustomerId,
                                        context = context,
                                        isArabic = isArabic
                                    )
                                )
                            }
                        },
                        onCancelClick = {
                            onDismissRequest()
                        }
                    )

                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(
                        start = MediumPadding,
                        end = MediumPadding,
                        bottom = if (isKeyboardOpen) 0.dp else MediumPadding
                    )
                    .animateContentSize(),
            ) {


                item {
                    CustomTextField(
                        modifier = Modifier.padding(bottom = SmallPadding),
                        value = state.selectedAddress.addressName,
                        onValueChange = {
                            event(AddressEvent.UpdateAddressName(it))
                        },
                        label = stringResource(id = R.string.addressName),
                        placeholder = stringResource(id = R.string.enterYourAddressName),
                        trailingIcon = {},
                        isError = !state.addressNameError.isNullOrEmpty(),
                        errorMessage = state.addressNameError?: ""
                    )
                }

                item {
                    Column(modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                        .padding(bottom = SmallPadding)) {
                        AddressDropMenuLabel(
                            name = state.selectedAddress.addressWilaya?.wilayaName ?: stringResource(
                                id = R.string.selectWilaya
                            ),
                            nameAr = state.selectedAddress.addressWilaya?.wilayaNameAr ?:stringResource(
                                id = R.string.selectWilaya
                            ),
                            isArabic = isArabic,
                            clickable = state.wilayaList.isNotEmpty(),
                            isError = !state.wilayaError.isNullOrEmpty(),
                            errorMessage = state.wilayaError?:""
                        ) {
                            event(AddressEvent.ToggleWilayaDropMenu)
                        }
                        Box(modifier = Modifier.align(Alignment.End)) {
                            DropdownMenu(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(horizontal = SmallPadding)
                                    .sizeIn(maxHeight = 300.dp)
                                    .animateContentSize(),
                                expanded = state.dropWilayaMenu, onDismissRequest = {
                                    event(AddressEvent.ToggleWilayaDropMenu)
                                }) {

                                state.wilayaList.forEach { wilaya ->
                                    DropdownMenuItem(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                if (state.selectedAddress.addressWilaya?.wilayaId == wilaya.wilayaId)
                                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                                else MaterialTheme.colorScheme.background,
                                                RoundedCornerShape(SmallPadding)
                                            )
                                            .clip(RoundedCornerShape(SmallPadding)),
                                        text = {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                            ) {
                                                Text(
                                                    text = if (isArabic) wilaya.wilayaNameAr else wilaya.wilayaName,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                                Text(
                                                    text = wilaya.wilayaId.toString(),
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                        }, onClick = {
                                            event(AddressEvent.SetWilaya(wilaya))
                                        })
                                }
                            }
                        }
                    }

                }

                item {
                    Column(modifier = Modifier.padding(bottom = Dimens.SmallPadding)){
                        AddressDropMenuLabel(
                            name = state.selectedAddress.addressCommune?.communeName ?: stringResource(
                                id = R.string.selectCommune
                            ),
                            nameAr = state.selectedAddress.addressCommune?.communeNameAr ?: stringResource(
                                id = R.string.selectCommune
                            ),
                            isArabic = isArabic,
                            clickable = state.selectedAddress.addressWilaya != null && state.communeList.isNotEmpty(),
                            isError = !state.communeError.isNullOrEmpty(),
                            errorMessage = state.communeError?:""
                        ) {
                            event(AddressEvent.ToggleCommuneDropMenu)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.End)
                        ) {
                            DropdownMenu(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(horizontal = SmallPadding)
                                    .sizeIn(maxHeight = 300.dp)
                                    .animateContentSize(),
                                expanded = state.dropCommuneMenu, onDismissRequest = {
                                    event(AddressEvent.ToggleCommuneDropMenu)
                                }) {
                                state.communeList.forEach { commune ->
                                    DropdownMenuItem(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                if (state.selectedAddress.addressCommune?.communeId == commune.communeId)
                                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                                else MaterialTheme.colorScheme.background,
                                                shape = RoundedCornerShape(SmallPadding)
                                            )
                                            .clip(RoundedCornerShape(SmallPadding)),
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                            ) {
                                                Text(
                                                    text = if (isArabic) commune.communeNameAr else commune.communeName,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                        }, onClick = {
                                            event(AddressEvent.SetCommune(commune))
                                        })
                                }
                            }
                        }
                    }
                }

                item {
                    CustomTextField(
                        modifier = Modifier.padding(bottom = SmallPadding),
                        value = state.selectedAddress.addressCodePostal,
                        onValueChange = {
                            if (it.length<=5){
                            event(AddressEvent.UpdateAddressCodePostal(it))
                            }
                        },
                        label = stringResource(id = R.string.postalCode),
                        placeholder = stringResource(id = R.string.enterYourPostalCode),
                        trailingIcon = {},
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.codePostalError.isNullOrEmpty(),
                        errorMessage = state.codePostalError ?:""
                    )
                }
                
                item { 
                    CustomTextField(
                        value = state.selectedAddress.addressSpecific,
                        onValueChange = {
                            event(AddressEvent.UpdateSpecificAddress(it))
                        },
                        label = stringResource(id = R.string.address) ,
                        placeholder = stringResource(id = R.string.enterYourAddress),
                        trailingIcon = {  },
                        isError = !state.specificAddressError.isNullOrEmpty(),
                        errorMessage = state.specificAddressError ?:""
                    )
                }

                if ( state.items.isEmpty() ||state.items[state.defaultAddressIndex] != state.selectedAddress.copy(userId = userId)) {
                    item {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = MediumPadding,
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = state.selectedAddress.addressDefault == 1,
                                onCheckedChange = {
                                    event(AddressEvent.UpdateAddressDefault(it))
                                })
                            Text(
                                text = stringResource(id = R.string.setMainAddress),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.offset(-Dimens.ExtraSmallPadding)
                            )
                        }
                    }
                }
            }
        }


    }

}

@Composable
private fun ColumnScope.DeleteIcon(
    addressDefault: Int, addressId: Int,
    userId: Int, event: (AddressEvent) -> Unit,
) {
    val context = LocalContext.current
    Icon(
        imageVector = Icons.Default.Delete, contentDescription = null,
        modifier = Modifier
            .padding(bottom = SmallPadding)
            .clip(CircleShape)
            .align(Alignment.CenterHorizontally)
            .size(SocialMediaItemSize)
            .padding(ExtraSmallPadding2)
            .clickable {
                if (addressDefault == 1) {
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
                            addressId = addressId,
                            userId = userId
                        )
                    )
                    event(AddressEvent.ToggleShowBottomSheet())
                }
            },
        tint = MaterialTheme.colorScheme.error
    )
}