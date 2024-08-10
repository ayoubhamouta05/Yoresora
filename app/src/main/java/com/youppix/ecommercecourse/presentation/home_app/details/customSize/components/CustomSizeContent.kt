package com.youppix.ecommercecourse.presentation.home_app.details.customSize.components

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.CustomSizeEvent
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.CustomSizeState

@Composable
fun CustomSizeContent(
    modifier: Modifier = Modifier,
    state: CustomSizeState,
    event: (CustomSizeEvent) -> Unit
) {
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
                        value = state.shoulderWidth,
                        label = stringResource(id = R.string.shoulderWidth),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.shoulderWidth)
                    ) {
                        event(CustomSizeEvent.UpdateShoulderWidth(it))
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.chestCircumference,
                        label = stringResource(id = R.string.chestCircumference),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.chestCircumference)
                    ) {
                        event(CustomSizeEvent.UpdateChestCircumference(it))
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.chestHeight,
                        label = stringResource(id = R.string.chestHeight),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.chestHeight)
                    ) {
                        event(CustomSizeEvent.UpdateChestHeight(it))
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
                        value = state.waistLine,
                        label = stringResource(id = R.string.waistLine),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.waistLine)
                    ) {
                        event(CustomSizeEvent.UpdateWaistLine(it))
                    }
                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.buttocksCircumference,
                        label = stringResource(id = R.string.buttocksCircumference),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.buttocksCircumference)
                    ) {
                        event(CustomSizeEvent.UpdateButtocksCircumference(it))
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.buttocksHeight,
                        label = stringResource(id = R.string.buttocksHeight),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.buttocksHeight)

                    ) {
                        event(CustomSizeEvent.UpdateButtocksHeight(it))
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
                        value = state.armCircumference,
                        label = stringResource(id = R.string.armCircumference),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.armCircumference)

                    ) {
                        event(CustomSizeEvent.UpdateArmCircumference(it))
                    }


                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.wristCircumference,
                        label = stringResource(id = R.string.wristCircumference),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.wristCircumference)
                    ) {
                        event(CustomSizeEvent.UpdateWristCircumference(it))
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.desiredArmLength,
                        label = stringResource(id = R.string.desiredArmLength),
                        imeAction = ImeAction.Next,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        isError = checkIfNumber(state.desiredArmLength)
                    ) {
                        event(CustomSizeEvent.UpdateDesiredArmLength(it))
                    }

                    CustomSizeTextField(
                        modifier = Modifier,
                        value = state.totalLength.toString(),
                        label = stringResource(id = R.string.totalLength),
                        imeAction = ImeAction.Next,
                        readOnly = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    ) {
                        event(CustomSizeEvent.UpdateTotalLength(it.toFloat()))
                    }

                }
            }

        }
    }

}

private fun checkIfNumber(values: String): Boolean {
    return try {
        if (values.isNotEmpty()) {
            values.toFloat()
        }
        false
    } catch (e: Exception) {
        true
    }
}