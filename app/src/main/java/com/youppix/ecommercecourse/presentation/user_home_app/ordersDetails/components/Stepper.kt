import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Step(
    modifier: Modifier = Modifier,
    isFirstStep: Boolean,
    isLastStep: Boolean,
    isComplete: Boolean,
    isCurrent: Boolean,
) {
    val color = if (isComplete || isCurrent) MaterialTheme.colorScheme.primary else Color.LightGray
    val innerCircleColor = if (isComplete) MaterialTheme.colorScheme.primary else Color.LightGray

    Box(
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(color)
                .align(
                    Alignment.CenterStart
                )
        )

        //Stage step circle
        if (isComplete || isCurrent ) {
            Icon(
                imageVector = if (isComplete) Icons.Default.CheckCircle else Icons.Default.Cancel,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterEnd)
                    .border(
                        shape = CircleShape,
                        width = 5.dp,
                        color = color
                    )
                    .background(MaterialTheme.colorScheme.background) ,
                tint =  if (isComplete) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
        else
            Canvas(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterEnd)
                    .border(
                        shape = CircleShape,
                        width = 5.dp,
                        color = color
                    ),
                onDraw = {
                    drawCircle(
                        color = innerCircleColor
                    )
                }
            )
    }
}

@Composable
fun StepProgressBar(
    modifier: Modifier = Modifier,
    numberOfSteps: Int,
    currentStep: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(numberOfSteps) { step ->
            Step(
                isComplete = step < currentStep,
                isCurrent = step == currentStep,
                isLastStep = step == numberOfSteps - 1,
                isFirstStep = step == 0,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


//                        StepProgressBar(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = MediumPadding),
//                            numberOfSteps = 3,
//                            currentStep = currentStep.value
//                        )
//
//                        Spacer(modifier = Modifier.height(50.dp))
//
//                        Row {
//                            Button(
//                                onClick = { currentStep.value++ }
//                            ) {
//                                Text(text = "Click Me +!")
//                            }
//                            Button(
//                                onClick = { currentStep.value-- }
//                            ) {
//                                Text(text = "Click Me -!")
//                            }
//                        }