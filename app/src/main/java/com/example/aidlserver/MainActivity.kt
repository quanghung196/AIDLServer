package com.example.aidlserver

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aidlserver.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.activity = this
    }

    fun onButtonClicked(view: View) {
        sendColor()
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        Log.d(TAG, "getColor: $color")
        return color
    }

    private fun sendColor() {
        val intent = Intent(ACTION)
        intent.apply {
            putExtra(COLOR_VALUE, getRandomColor())
        }
        val packageManager = packageManager
        val resolverInfoList = packageManager.queryBroadcastReceivers(intent, 0)
        for (info in resolverInfoList) {
            val componentName = ComponentName(info.activityInfo.packageName, info.activityInfo.name)
            intent.component = componentName
            sendBroadcast(intent)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val ACTION = "com.example.aidlserver"
        private const val COLOR_VALUE = "color"
    }
}