package kz.narxoz.android1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

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
