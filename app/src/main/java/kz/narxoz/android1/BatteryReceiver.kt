//package kz.narxoz.android1
//
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//
//class BatteryReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        // Получаем данные о батарее из интента
//        val batteryPercentage = intent.getIntExtra("battery_percentage", 0)
//
//        // Здесь вы можете обновить UI или уведомить слушателей о новом проценте батареи
//        // Например, отправить локальное уведомление или обновить ViewModel
//        val activity = context as? MainActivity
//        activity?.updateBatteryPercentage(batteryPercentage)
//    }
//}
