//package kz.narxoz.android1
//
//import android.app.Service
//import android.content.Intent
//import android.content.IntentFilter
//import android.os.BatteryManager
//import android.os.IBinder
//
//class BatteryService : Service() {
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        // Регистрируем фильтр для получения статуса батареи
//        val batteryStatus = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
//            applicationContext.registerReceiver(null, ifilter)
//        }
//
//        // Получаем процент заряда батареи
//        val level: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
//
//        // Отправляем обновление батареи через локальную передачу
//        val batteryUpdateIntent = Intent("battery_update")
//        batteryUpdateIntent.putExtra("battery_percentage", level)
//        sendBroadcast(batteryUpdateIntent)
//
//        // Возвращаем START_NOT_STICKY, чтобы сервис не перезапускался автоматически
//        return START_NOT_STICKY
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//}
