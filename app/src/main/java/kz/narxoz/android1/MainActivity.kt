package kz.narxoz.android1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivityLifecycle"
    private var batteryReceiver: BroadcastReceiver? = null
    private var isDetailScreenVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity is created")
        enableEdgeToEdge()
        setContent {
            MyApp(onRouteChange = { route ->
                handleRouteChange(route)
            })
        }

        // Register the battery receiver
//        batteryReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                val batteryPercentage = intent.getIntExtra("battery_percentage", 0)
//                updateBatteryPercentage(batteryPercentage)
//            }
//        }

//        val filter = IntentFilter("battery_update")
//        registerReceiver(batteryReceiver, filter)

        // Start the BatteryService to begin monitoring battery updates
//        val batteryServiceIntent = Intent(this, BatteryService::class.java)
//        startService(batteryServiceIntent)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity became visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity has resumed and the user can interact.")
        if (isDetailScreenVisible) {
            Log.d(TAG, "Resumed on Detail Screen")
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity paused, saving data if needed")
        if (isDetailScreenVisible) {
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity no longer visible, releasing resources")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity destroyed")
        // Unregister the receiver when the activity is destroyed
        batteryReceiver?.let {
            unregisterReceiver(it)
        }
    }

    private fun handleRouteChange(route: String) {
        isDetailScreenVisible = route.startsWith("detail")
        if (isDetailScreenVisible) {
            Log.d(TAG, "Navigating to Detail Screen")
        } else {
            Log.d(TAG, "Navigating to another screen")
        }
    }

    // Function to handle battery percentage updates
    fun updateBatteryPercentage(percentage: Int) {
        // Display battery percentage using Toast or another UI update method
        Toast.makeText(this, "Battery Percentage: $percentage%", Toast.LENGTH_SHORT).show()
    }
}
