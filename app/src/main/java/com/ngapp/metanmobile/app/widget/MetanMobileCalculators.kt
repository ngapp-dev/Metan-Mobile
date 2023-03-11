package com.ngapp.metanmobile.app.widget

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.charts.HorizontalBarChartItem
import com.ngapp.metanmobile.app.widget.charts.SimpleHorizontalBarChartView
import timber.log.Timber
import kotlin.math.roundToInt

private enum class CalculatorTabs(@StringRes val value: Int) {
    CALCULATE_MILEAGE(R.string.title_calculate_milage),
    CALCULATE_PAYBACK(R.string.title_calculate_payback)
}

@Composable
fun MetanMobileCalculators(
    modifier: Modifier = Modifier,
    tabRowIndicatorColor: Color,
    tabNameColor: Color
) {
    Column {
        val tabsName = remember { CalculatorTabs.values().map { it.value } }
        val selectedIndex =
            rememberSaveable { mutableStateOf(CalculatorTabs.CALCULATE_MILEAGE.ordinal) }
        TabRow(
            selectedTabIndex = selectedIndex.value,
            backgroundColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = tabRowIndicatorColor,
                    height = TabRowDefaults.IndicatorHeight,
                    modifier = modifier
                        .tabIndicatorOffset(tabPositions[selectedIndex.value])
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp),
                )
            }
        ) {
            tabsName.forEachIndexed { index, stringResourceId ->
                Tab(
                    selected = index == selectedIndex.value,
                    onClick = {
                        when (stringResourceId) {
                            CalculatorTabs.CALCULATE_MILEAGE.value -> {
                                selectedIndex.value =
                                    CalculatorTabs.CALCULATE_MILEAGE.ordinal
                            }
                            CalculatorTabs.CALCULATE_PAYBACK.value -> {
                                selectedIndex.value =
                                    CalculatorTabs.CALCULATE_PAYBACK.ordinal
                            }

                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = stringResourceId),
                            style = MetanMobileTypography.h3,
                            color = tabNameColor,
                        )
                    }
                )
            }
        }
        when (selectedIndex.value) {
            CalculatorTabs.CALCULATE_MILEAGE.ordinal -> {
                CalculatorMileagePage(modifier)
            }
            CalculatorTabs.CALCULATE_PAYBACK.ordinal -> {
                CalculatorPaybackPage(modifier)
            }
        }
    }
}


@Composable
private fun CalculatorMileagePage(
    modifier: Modifier = Modifier,
) {
    var showResult by remember { mutableStateOf(false) }
    var selectedCarType by remember { mutableStateOf("") }
    var inputAmount by remember { mutableStateOf("") }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 0.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MetanMobileShapes.large)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            if (!showResult) {
                CalculatorMileageEnterValueView(
                    onCalculate = {
                        selectedCarType = it.first
                        inputAmount = it.second
                        showResult = it.third
                    }
                )
            } else {
                CalculatorMileageResultView(
                    carTypes = listOf(
                        stringResource(R.string.text_car_personal),
                        stringResource(R.string.text_car_truck),
                        stringResource(R.string.text_car_lorry)
                    ),
                    selectedCarType = selectedCarType,
                    inputAmount = inputAmount.toDouble(),
                    onCalculateAgain = { showResult = it }
                )
            }
        }
    }
}

@Composable
private fun CalculatorPaybackPage(
    modifier: Modifier = Modifier,
) {
    var fuelPrice by remember { mutableStateOf("") }
    var additionalCosts by remember { mutableStateOf("") }
    var estimatedMileage by remember { mutableStateOf("") }
    var fuelConsumption by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 0.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MetanMobileShapes.large)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (!showResult) {
                CalculatorPaybackEnterValueView(
                    onCalculate = { inputFuelPrice, inputAdditionalCosts, inputEstimatedMileage, inputFuelConsumption, showResultValue ->
                        fuelPrice = inputFuelPrice
                        additionalCosts = inputAdditionalCosts
                        estimatedMileage = inputEstimatedMileage
                        fuelConsumption = inputFuelConsumption
                        showResult = showResultValue
                    }
                )
            } else {
                CalculatePaybackProfit(
                    fuelPrice = fuelPrice.toDouble(),
                    additionalCosts = additionalCosts.toDouble(),
                    estimatedMileage = estimatedMileage.toDouble(),
                    fuelConsumption = fuelConsumption.toDouble(),
                    onCalculateAgain = { showResult = it }
                )
            }
        }
    }
}

@Composable
private fun CalculatorMileageEnterValueView(
    modifier: Modifier = Modifier,
    onCalculate: (Triple<String, String, Boolean>) -> Unit,
) {
    val context = LocalContext.current

    val carTypes = listOf(
        stringResource(R.string.text_car_personal),
        stringResource(R.string.text_car_truck),
        stringResource(R.string.text_car_lorry)
    )

    val (selected, setSelected) = remember { mutableStateOf(carTypes[0]) }

    var inputAmount by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.text_choose_car_type),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )

        CarTypesRadioGroup(items = carTypes, selected = selected, setSelected = setSelected)

        Text(
            text = stringResource(R.string.text_enter_refill_amount),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputAmount,
            onValueChange = { inputAmount = it },
            placeholder = {
                Text(stringResource(id = R.string.placeholder_number))
            },
            shape = MetanMobileShapes.large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Gray400,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Gray400
            )
        )
        ButtonWithIcon(
            icon = Icons.Filled.Calculate,
            iconTint = White,
            buttonText = R.string.button_calculate,
            buttonBackgroundColor = Blue,
            fontColor = White,
            borderStrokeColor = Blue,
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                if (inputAmount.text.isNotEmpty()) {
                    onCalculate.invoke(Triple(selected, inputAmount.text, it))
                } else {
                    Toast.makeText(context, R.string.text_values_error, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
private fun CalculatorPaybackEnterValueView(
    modifier: Modifier = Modifier,
    onCalculate: (String, String, String, String, Boolean) -> Unit,
) {
    val context = LocalContext.current

    var inputFuelPrice by remember { mutableStateOf(TextFieldValue()) }
    var inputAdditionalCosts by remember { mutableStateOf(TextFieldValue()) }
    var inputEstimatedMileage by remember { mutableStateOf(TextFieldValue()) }
    var inputFuelConsumption by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.text_fuel_price_by_vehicle),
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputFuelPrice,
            onValueChange = { inputFuelPrice = it },
            placeholder = {
                Text(stringResource(id = R.string.placeholder_number))
            },
            shape = MetanMobileShapes.large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Gray400,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Gray400
            )
        )
        Text(
            text = stringResource(R.string.text_additional_costs),
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputAdditionalCosts,
            onValueChange = { inputAdditionalCosts = it },
            placeholder = {
                Text(stringResource(id = R.string.placeholder_number))
            },
            shape = MetanMobileShapes.large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Gray400,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Gray400
            )
        )
        Text(
            text = stringResource(R.string.text_estimated_vehicle_milage),
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputEstimatedMileage,
            onValueChange = { inputEstimatedMileage = it },
            placeholder = {
                Text(stringResource(id = R.string.placeholder_number))
            },
            shape = MetanMobileShapes.large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Gray400,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Gray400
            )
        )
        Text(
            text = stringResource(R.string.text_vehicle_consumption),
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputFuelConsumption,
            onValueChange = { inputFuelConsumption = it },
            placeholder = { Text(stringResource(id = R.string.placeholder_number)) },
            shape = MetanMobileShapes.large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Gray400,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Gray400
            )
        )
        Text(
            text = stringResource(R.string.text_payback_calc_note),
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.subtitle1
        )
        ButtonWithIcon(
            icon = Icons.Filled.Calculate,
            iconTint = White,
            buttonText = R.string.button_calculate,
            buttonBackgroundColor = Blue,
            fontColor = White,
            borderStrokeColor = Blue,
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                if (inputFuelPrice.text.isNotEmpty() && inputAdditionalCosts.text.isNotEmpty() &&
                    inputEstimatedMileage.text.isNotEmpty() &&
                    inputFuelConsumption.text.isNotEmpty()
                ) {
                    onCalculate.invoke(
                        inputFuelPrice.text,
                        inputAdditionalCosts.text,
                        inputEstimatedMileage.text,
                        inputFuelConsumption.text,
                        it
                    )
                } else {
                    Toast.makeText(context, R.string.text_values_error, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CalculatorMileageResultView(
    modifier: Modifier = Modifier,
    carTypes: List<String>,
    selectedCarType: String,
    inputAmount: Double,
    onCalculateAgain: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.text_car_milage_amount, inputAmount),
            overflow = TextOverflow.Ellipsis,
            style = MetanMobileTypography.h3
        )
        SimpleHorizontalBarChartView(
            data = getCalculatorMileageResult(context, carTypes, selectedCarType, inputAmount),
            height = 272.dp,
            topStartRadius = 12.dp,
            topEndRadius = 12.dp,
            bottomEndRadius = 12.dp,
            bottomStartRadius = 12.dp,
//            backgroundColor = MetanMobileColors.background,
            modifier = Modifier.padding(top = 16.dp)
        )
        ButtonWithIcon(
            icon = Icons.Filled.Calculate,
            iconTint = White,
            buttonText = R.string.button_calculate_again,
            buttonBackgroundColor = Blue,
            fontColor = White,
            borderStrokeColor = Blue,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            onClick = { onCalculateAgain.invoke(!it) }
        )
    }
}

private fun getCalculatorMileageResult(
    context: Context,
    carTypes: List<String>,
    selectedCarType: String,
    inputAmount: Double,
): List<HorizontalBarChartItem> {
    val readyCharItems = mutableListOf<HorizontalBarChartItem>()
    val fuelPrices =
        listOf(
            Pair(context.getString(R.string.text_cng), 1.06),
            Pair(context.getString(R.string.text_dt), 2.46),
            Pair(context.getString(R.string.text_ai95), 2.46),
            Pair(context.getString(R.string.text_ai92), 2.36),
            Pair(context.getString(R.string.text_lpg), 1.32)
        )

    when (selectedCarType) {
        carTypes[0] -> {
            val fuelRates = listOf(
                Triple(
                    context.getString(R.string.text_cng),
                    8.7,
                    context.getString(R.string.text_on_cng)
                ),
                Triple(
                    context.getString(R.string.text_dt),
                    5.7,
                    context.getString(R.string.text_on_dt)
                ),
                Triple(
                    context.getString(R.string.text_ai95),
                    8.1,
                    context.getString(R.string.text_on_ai95)
                ),
                Triple(
                    context.getString(R.string.text_ai92),
                    8.1,
                    context.getString(R.string.text_on_ai92)
                ),
                Triple(
                    context.getString(R.string.text_lpg),
                    10.4,
                    context.getString(R.string.text_on_lpg)
                )
            )

            fuelRates.forEachIndexed { index, element ->
                val formula =
                    (inputAmount * 100) / (fuelRates[index].second * fuelPrices[index].second)
                readyCharItems.add(
                    HorizontalBarChartItem(
                        id = index,
                        active = element.first == context.getString(R.string.text_cng),
                        title = element.third,
                        value = formula.toFloat()
                    )
                )
            }
        }
        carTypes[1] -> {
            val fuelRates = listOf(
                Triple(
                    context.getString(R.string.text_cng),
                    18.0,
                    context.getString(R.string.text_on_cng)
                ),
                Triple(
                    context.getString(R.string.text_dt),
                    11.5,
                    context.getString(R.string.text_on_dt)
                ),
                Triple(
                    context.getString(R.string.text_ai95),
                    16.0,
                    context.getString(R.string.text_on_ai95)
                ),
                Triple(
                    context.getString(R.string.text_ai92),
                    16.0,
                    context.getString(R.string.text_on_ai92)
                ),
                Triple(
                    context.getString(R.string.text_lpg),
                    19.2,
                    context.getString(R.string.text_on_lpg)
                )
            )
            fuelRates.forEachIndexed { index, element ->
                val formula =
                    (inputAmount * 100) / (fuelRates[index].second * fuelPrices[index].second)
                readyCharItems.add(
                    HorizontalBarChartItem(
                        id = index,
                        active = element.first == context.getString(R.string.text_cng),
                        title = element.third,
                        value = formula.toFloat()
                    )
                )
            }
        }
        carTypes[2] -> {
            val fuelRates = listOf(
                Triple(
                    context.getString(R.string.text_cng),
                    32.0,
                    context.getString(R.string.text_on_cng)
                ),
                Triple(
                    context.getString(R.string.text_dt),
                    25.0,
                    context.getString(R.string.text_on_dt)
                ),
                Triple(
                    context.getString(R.string.text_ai95),
                    31.0,
                    context.getString(R.string.text_on_ai95)
                ),
                Triple(
                    context.getString(R.string.text_ai92),
                    31.0,
                    context.getString(R.string.text_on_ai92)
                ),
                Triple(
                    context.getString(R.string.text_lpg),
                    42.0,
                    context.getString(R.string.text_on_lpg)
                )
            )
            fuelRates.forEachIndexed { index, element ->
                val formula =
                    (inputAmount * 100) / (fuelRates[index].second * fuelPrices[index].second)
                readyCharItems.add(
                    HorizontalBarChartItem(
                        id = index,
                        active = element.first == context.getString(R.string.text_cng),
                        title = element.third,
                        value = formula.toFloat()
                    )
                )
            }
        }
    }
    return readyCharItems
}

@Composable
private fun CalculatePaybackProfit(
    modifier: Modifier = Modifier,
    fuelPrice: Double,
    additionalCosts: Double,
    estimatedMileage: Double,
    fuelConsumption: Double,
    onCalculateAgain: (Boolean) -> Unit,
) {

    val left = (fuelConsumption * estimatedMileage * fuelPrice / 100)
    val right = (fuelConsumption * estimatedMileage * 1.06 / 100)

    val paybackTime = ((additionalCosts / (left - right)) * 12).roundToInt()

    val paybackMileage =
        ((additionalCosts * 100) / ((fuelConsumption * fuelPrice) - (fuelConsumption * 1.06))).roundToInt()

    CalculatorPaybackResultView(
        paybackTime = paybackTime,
        paybackMileage = paybackMileage,
        onCalculateAgain = onCalculateAgain
    )
}

@Composable
private fun CalculatorPaybackResultView(
    modifier: Modifier = Modifier,
    paybackTime: Int,
    paybackMileage: Int,
    onCalculateAgain: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val paybackTimeMonth = when (paybackTime) {
            1 -> {
                R.string.text_value_month1
            }
            in 2..3 -> {
                R.string.text_value_month2_4
            }
            else -> {
                R.string.text_value_month
            }
        }
        Text(
            text = stringResource(paybackTimeMonth, paybackTime.toString()),
            style = MetanMobileTypography.h3
        )
        Text(
            text = stringResource(R.string.text_or),
            style = MetanMobileTypography.h3
        )
        Text(
            text = stringResource(id = R.string.text_value_km, paybackMileage.toString()),
            style = MetanMobileTypography.h3
        )
        ButtonWithIcon(
            icon = Icons.Filled.Calculate,
            iconTint = White,
            buttonText = R.string.button_calculate_again,
            buttonBackgroundColor = Blue,
            fontColor = White,
            borderStrokeColor = Blue,
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = { onCalculateAgain.invoke(!it) }
        )
    }
}