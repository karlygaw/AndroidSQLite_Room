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
    }
    private fun handleRouteChange(route: String) {
        isDetailScreenVisible = route.startsWith("detail")
        if (isDetailScreenVisible) {
            Log.d(TAG, "Navigating to Detail Screen")
        } else {
            Log.d(TAG, "Navigating to another screen")
        }
    }
}
