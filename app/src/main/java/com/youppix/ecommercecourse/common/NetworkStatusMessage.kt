import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifi4Bar
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkStatusMessage(status: NetworkConnectivityManager.Status) {

    var visible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var networkStatusMessage by remember {
        mutableStateOf("")
    }

    LaunchedEffect(status) {
        if (status == NetworkConnectivityManager.Status.Lost || status == NetworkConnectivityManager.Status.Unavailable) {
            visible = true

        } else if (status == NetworkConnectivityManager.Status.Available) {
            visible = true
            scope.launch {
                delay(3000)
                visible = false
            }
        } else {
            visible = true
        }
    }

    val backgroundColor = when (status) {
        NetworkConnectivityManager.Status.Lost -> {
            networkStatusMessage = stringResource(id = R.string.networkStatusUnavailable)
            MaterialTheme.colorScheme.error
        }
        NetworkConnectivityManager.Status.Unavailable -> {
            networkStatusMessage = stringResource(id = R.string.networkStatusUnavailable)
            MaterialTheme.colorScheme.error
        }
        NetworkConnectivityManager.Status.Available -> {
            networkStatusMessage = stringResource(id = R.string.networkStatusAvailable)
            Color.Green
        }
    }

    Row(
        modifier = Modifier
            .padding(
                top = TopAppBarDefaults.windowInsets
                    .asPaddingValues()
                    .calculateTopPadding()
                    .plus(
                        TopAppBarDefaults.windowInsets
                            .asPaddingValues()
                            .calculateTopPadding()
                    )
            )
            .animateContentSize()
            .height(if (visible) 35.dp else 0.dp)
            .fillMaxWidth()
            .background(
                backgroundColor,
                shape = RoundedCornerShape(bottomEnd = 35.dp, bottomStart = 35.dp)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if (status == NetworkConnectivityManager.Status.Available)
                Icons.Default.SignalWifi4Bar
            else
                Icons.Default.SignalWifiConnectedNoInternet4, contentDescription = null,
            tint = Color.White
        )
        Text(
            text = networkStatusMessage,
            style = MaterialTheme.typography.bodyMedium.copy(

            ),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(start = SmallPadding)
        )
    }

}
