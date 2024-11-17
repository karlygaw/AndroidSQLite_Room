package kz.narxoz.android1

import android.content.Context
import android.content.Intent
import android.content.BroadcastReceiver
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import android.content.IntentFilter
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
@Composable
fun MyAccountScreen() {
    // State to hold battery percentage
    var batteryPercentage by remember { mutableStateOf(0) }

    // Box to layout components
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_photo),
                contentDescription = "User Photo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Ussen Qarlygash",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Show battery percentage
            Text(
                text = "Battery: $batteryPercentage%",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
