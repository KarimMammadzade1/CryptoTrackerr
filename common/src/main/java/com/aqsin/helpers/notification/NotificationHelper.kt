package com.aqsin.helpers.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.aqsin.helpers.Constants
import com.aqsin.helpers.R
import java.util.*

object NotificationHelper {

    fun makePushNotification(title: String, message: String?, context: Context) {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                Constants.NotificationChannelId,
                Constants.NotificationChannelName, NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = Constants.NotificationChannelDescription
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context,
            Constants.NotificationChannelId)
            .setSmallIcon(R.drawable.ic_notfication)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(alarmSound)
            .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val random = Random()
        val ID = random.nextInt()

        NotificationManagerCompat.from(context).notify(ID, builder.build())
    }

}