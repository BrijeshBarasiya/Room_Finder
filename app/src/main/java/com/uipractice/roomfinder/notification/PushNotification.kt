package com.uipractice.roomfinder.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.authentication.view.LoginActivity

class PushNotification: FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {
            showNotification(message.notification!!.title!!, message.notification!!.body!!)
        }
    }

    private fun showNotification(title: String, subtitle: String) {
        NotificationCreator(this, "Hello", R.drawable.ic_launcher_background, title, subtitle).addAction("Hello", LoginActivity::class.java).createNotification()
    }

}