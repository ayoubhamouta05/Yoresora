package com.youppix.ecommercecourse.presentation.admin_home_app.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youppix.ecommercecourse.common.Constant.randomColor
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer


@Composable
fun StatisticsGraph(modifier: Modifier = Modifier) {
    Chart(modifier = modifier)
}

@Composable
fun Chart(modifier: Modifier = Modifier) {
    BarChart(
        modifier = modifier,
        barChartData = BarChartData(spendingByDay),
        xAxisDrawer = SimpleXAxisDrawer(
            axisLineThickness = 2.dp,
            axisLineColor = MaterialTheme.colorScheme.onBackground.copy(0.2f)
        ),
        yAxisDrawer = SimpleYAxisDrawer(
            labelValueFormatter = { "$ ${it.toInt()}" },
            axisLineThickness = 2.dp,
            axisLineColor = MaterialTheme.colorScheme.onBackground.copy(0.2f)
        ),
        labelDrawer = SimpleLabelDrawer(
            labelTextSize = 10.sp,
            drawLocation = SimpleLabelDrawer.DrawLocation.Outside
        )
    )
}


val spendingByDay = listOf(
    BarChartData.Bar(
        label = "Dec 1",
        value = 123f,
        color = randomColor(90),
    ),
    BarChartData.Bar(
        label = "Dec 2",
        value = 160f,
        color = randomColor(90),
    ),
    BarChartData.Bar(
        label = "Dec 3",
        value = 204f,
        color = randomColor(90),
    ),
    BarChartData.Bar(
        label = "Dec 4",
        value = 34f,
        color = randomColor(90),
    ),
    BarChartData.Bar(
        label = "Dec 5",
        value = 74f,
        color = randomColor(90),
    ),
    BarChartData.Bar(
        label = "Dec 6",
        value = 34f,
        color = randomColor(90),
    ),
    BarChartData.Bar(
        label = "Dec 7",
        value = 74f,
        color = randomColor(90),
    )
)