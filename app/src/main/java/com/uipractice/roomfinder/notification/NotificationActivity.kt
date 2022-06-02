package com.uipractice.roomfinder.notification

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    // Variable
    private lateinit var binding: ActivityNotificationBinding
    private var count = 0

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        val view = binding.root
        binding.onClicked = this
        setContentView(view)
    }

    override fun onClick(view: View) {
        count++
        when (view.id) {
            binding.btnLocalNotification.id -> progressBar()
            binding.btnPushNotification.id -> {
                val demo = NotificationCreator(this, "Hello", R.drawable.ic_notification, "Title", "Message")
                demo.createNotification()
                demo.addNotification("Title", "Message")
                demo.addNotification("Title2", "Message2")
                demo.addNotification("Title3", "Message3")
                demo.addNotification("Title4", "Message4")
                demo.addNotification("Title5", "Message5")
                demo.addNotification("Title6", "Message6")
                demo.addNotification("Title7", "Message7")
            }
        }
    }

    private fun progressBar(){
        val progressBar = NotificationCreator(this, "Hello", R.drawable.image, "Hello", "I am Message").addProgressBar()
        progressBar.createNotification()
        var currentProgress = 0
        Thread(Runnable{
            while (currentProgress < 100) {
                SystemClock.sleep(1000)
                currentProgress += 25
                progressBar.setProgressValue(100, currentProgress)
            }
            progressBar.setProgressValue(0, 0)
        }).start()
    }

}