package com.youppix.ecommercecourse.presentation.home_app.customSize.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.customSize.CustomSizeErrorsState
import com.youppix.ecommercecourse.presentation.home_app.customSize.CustomSizeEvent
import com.youppix.ecommercecourse.presentation.home_app.customSize.CustomSizeState

@Composable
fun CustomSizeContent(
    modifier: Modifier = Modifier,
    state: CustomSizeState,
    event: (CustomSizeEvent) -> Unit
) {
    val context = LocalContext.current
    var showTopBodyInfo by remember {
        mutableStateOf(true)
    }
    var showBottomBodyInfo by remember {
        mutableStateOf(false)
    }
    var showGeneralInformation by remember {
        mutableStateOf(false)
    }
    val density = LocalDensity.current

    val enterTransition = slideInVertically {
        with(density) { -40.dp.roundToPx() }
    } + expandVertically(
        expandFrom = Alignment.Top
    ) + fadeIn(
        initialAlpha = 0.3f
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = SmallPadding),
        contentPadding = PaddingValues(SmallPadding)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
                    .clip(CircleShape)
                    .clickable {
                        showTopBodyInfo = !showTopBodyInfo
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.topSection),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(horizontal = SmallPadding)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(if (showTopBodyInfo) 0f else 180f)
                )
            }
            AnimatedVisibility(
                showTopBodyInfo, enter = enterTransition,
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Column {

                    CustomSizeTextField(
                        modifier = Modifier,

                        value = state.size.shoulderWidth,
                        label = stringResource(id = R.string.shoulderWidth),
                        errorMessage = state.errors.shoulderWidth ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.shoulderWidth.isNullOrEmpty()
                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(shoulderWidth = it)))
                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 35f,
                            max = 49f,
                            name = context.getString(R.string.shoulderWidth)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        shoulderWidth = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.chestCircumference,
                        label = stringResource(id = R.string.chestCircumference),
                        errorMessage = state.errors.chestCircumference ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.chestCircumference.isNullOrEmpty()
                    ) {
                        event(
                            CustomSizeEvent.UpdateSizeInformation(
                                state.size.copy(
                                    chestCircumference = it
                                )
                            )
                        )
                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            max = 120f,
                            min = 80f,
                            name = context.getString(R.string.chestCircumference)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        chestCircumference = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.chestHeight,
                        label = stringResource(id = R.string.chestHeight),
                        errorMessage = state.errors.chestHeight ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.chestHeight.isNullOrEmpty()
                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(chestHeight = it)))
                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 22f,
                            max = 30f,
                            name = context.getString(R.string.chestHeight)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        chestHeight = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }
                }
            }
        }
        // Bottom Section
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clip(CircleShape)
                    .clickable {
                        showBottomBodyInfo = !showBottomBodyInfo
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.bottomSection),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(horizontal = SmallPadding)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown, contentDescription = null,
                    modifier = Modifier.rotate(if (showBottomBodyInfo) 0f else 180f)
                )
            }
            AnimatedVisibility(
                showBottomBodyInfo, enter = enterTransition,
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Column {

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.waistLine,
                        label = stringResource(id = R.string.waistLine),
                        errorMessage = state.errors.waistLine ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.waistLine.isNullOrEmpty()
                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(waistLine = it)))

                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 62f,
                            max = 104f,
                            name = context.getString(R.string.waistLine)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        waistLine = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }
                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.buttocksCircumference,
                        errorMessage = state.errors.buttocksCircumference ?: "",
                        label = stringResource(id = R.string.buttocksCircumference),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.buttocksCircumference.isNullOrEmpty()
                    ) {
                        event(
                            CustomSizeEvent.UpdateSizeInformation(
                                state.size.copy(
                                    buttocksCircumference = it
                                )
                            )
                        )

                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 86f,
                            max = 126f,
                            name = context.getString(R.string.buttocksCircumference)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        buttocksCircumference = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.buttocksHeight,
                        errorMessage = state.errors.buttocksHeight ?: "",
                        label = stringResource(id = R.string.buttocksHeight),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.buttocksHeight.isNullOrEmpty()

                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(buttocksHeight = it)))

                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 70f,
                            max = 86f,
                            name = context.getString(R.string.buttocksHeight)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        buttocksHeight = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }

                }
            }


        }
        // General Information
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SmallPadding)
                    .clip(CircleShape)
                    .clickable {
                        showGeneralInformation = !showGeneralInformation
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.generalInformation),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(horizontal = SmallPadding)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown, contentDescription = null,
                    modifier = Modifier.rotate(if (showGeneralInformation) 0f else 180f)
                )
            }
            AnimatedVisibility(
                showGeneralInformation, enter = enterTransition,
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Column {

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.armCircumference,
                        label = stringResource(id = R.string.armCircumference),
                        errorMessage = state.errors.armCircumference ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.armCircumference.isNullOrEmpty()

                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(armCircumference = it)))
                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 23f,
                            max = 37f,
                            name = context.getString(R.string.armCircumference)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        armCircumference = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }


                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.wristCircumference,
                        label = stringResource(id = R.string.wristCircumference),
                        errorMessage = state.errors.wristCircumference ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.wristCircumference.isNullOrEmpty()
                    ) {
                        event(
                            CustomSizeEvent.UpdateSizeInformation(
                                state.size.copy(
                                    wristCircumference = it
                                )
                            )
                        )

                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 14f,
                            max = 20f,
                            name = context.getString(R.string.wristCircumference)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        wristCircumference = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.desiredArmLength,
                        label = stringResource(id = R.string.desiredArmLength),
                        errorMessage = state.errors.desiredArmLength ?: "",
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = !state.errors.desiredArmLength.isNullOrEmpty()
                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(desiredArmLength = it)))
                        event(CustomSizeEvent.CheckInput(
                            context = context,
                            value = it,
                            min = 56f,
                            max = 68f,
                            name = context.getString(R.string.desiredArmLength)
                        ) { error ->
                            event(
                                CustomSizeEvent.UpdateErrorsState(
                                    state.errors.copy(
                                        desiredArmLength = error
                                    )
                                )
                            )
                            event(CustomSizeEvent.UpdateErrorMessage(error))
                        })
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.size.totalLength.toString(),
                        label = stringResource(id = R.string.totalLength),
                        imeAction = ImeAction.Next,
                        readOnly = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    ) {
                        event(CustomSizeEvent.UpdateSizeInformation(state.size.copy(totalLength = it.toFloat())))
                    }

                }
            }

        }
    }

}