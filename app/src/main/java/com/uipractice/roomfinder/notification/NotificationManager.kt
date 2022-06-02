package com.uipractice.roomfinder.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.uipractice.roomfinder.R

open class NotificationCreator(
    private val context: Context,
    private val channel: String,
    @DrawableRes private val icon: Int,
    title: String,
    message: String
): NotificationCompat.Builder(context, channel) {

    private var inboxStyle: NotificationCompat.InboxStyle = NotificationCompat.InboxStyle()
    private val notificationManager = NotificationManagerCompat.from(context)
    private var notificationId = 0

    init {
        this.setSmallIcon(icon)
        this.setContentTitle(title)
        this.setContentText(message)
        this.priority = NotificationCompat.PRIORITY_DEFAULT
    }

    fun addLargeIcon(image: Bitmap): NotificationCreator {
        this.setLargeIcon(image)
        return  this
    }

    fun <T> addAction(title: String, redirectTo: Class<T>): NotificationCreator {
        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, redirectTo), PendingIntent.FLAG_IMMUTABLE)
        this.addAction(icon, title, pendingIntent)
        return this
    }

    fun expandableNotification(image: Bitmap): NotificationCreator {
        this.setLargeIcon(image)
        this.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(image)
                .bigLargeIcon(null)
        )
        return this
    }

    fun addProgressBar(): NotificationCreator {
        this.setOngoing(false)
        this.setOnlyAlertOnce(true)
        this.setNumber(5)
        this.priority = NotificationCompat.PRIORITY_LOW
        this.setProgress(100, 0, false)
        return this
    }

    fun setProgressValue(max: Int, progress: Int) {
        this.setProgress(max, progress, false)
        createNotification()
    }

    fun createNotification() {
        notificationManager.notify(notificationId, this.build())
    }

    fun addMessage(message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channel, R.string.app_name.toString(), NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        inboxStyle.setBigContentTitle("New Message")
        inboxStyle.addLine(message)
        this.setStyle(inboxStyle)
        notificationManager.notify(1, this.build())
    }

    fun addNotification(title: String, message: String) {
        this.setStyle(NotificationCompat.InboxStyle())
        this.setContentTitle(title)
        this.setContentText(message)
        notificationId += 1
        createNotification()
    }

}