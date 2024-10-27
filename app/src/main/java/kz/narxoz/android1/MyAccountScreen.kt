package kz.narxoz.android1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyAccountScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User photo placeholder
        Image(
            painter = painterResource(id = R.drawable.user_photo), // Replace with your user image resource
            contentDescription = "User Photo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp)
        )

        // User name
        Text(
            text = "Ussen Qarlygash",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp)
        )
    }
}
