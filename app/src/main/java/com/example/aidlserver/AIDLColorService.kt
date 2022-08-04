package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import java.util.*


class AIDLColorService : Service() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder: IAIDLColorInterface.Stub = object : IAIDLColorInterface.Stub() {
        @Throws(RemoteException::class)
        override fun getColor(): Int {
            val rnd = Random()
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            Log.d(TAG, "getColor: $color")
            return color
        }
    }
}