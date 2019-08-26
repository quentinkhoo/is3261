package com.quentin.is3261.tutorial5

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.BatteryManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var textView: TextView

    private var myPreferences = "myPrefs"
    private var keyIsCharging = "isCharging"
    private var keyUSBCharging = "usbCharging"
    private var keyACCharging = "acCharging"
    private var keyBatteryPercentage = "batteryPercentage"
    private var keyTimeStamp = "timestamp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.textView)
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

        val btnSave = findViewById<Button>(R.id.save)
        btnSave.setOnClickListener {
            saveData()
        }

        val btnRetrieve = findViewById<Button>(R.id.retrieve)
        btnRetrieve.setOnClickListener {
            retrieveData()
        }
    }

    private fun saveData() {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
            ifilter -> this.registerReceiver(null, ifilter)
        }

        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)?: -1

        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)?: -1

        val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

        val batteryPct: Float = batteryStatus?.let{ intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level/scale.toFloat()
        }?: -1.0f

        val currentTimestamp: Long = System.currentTimeMillis()/1000;

        val editor = sharedPreferences.edit()

        editor.putBoolean(keyIsCharging, isCharging)
        editor.putBoolean(keyUSBCharging, usbCharge)
        editor.putBoolean(keyACCharging, acCharge)
        editor.putFloat(keyBatteryPercentage, batteryPct)
        editor.putLong(keyTimeStamp, currentTimestamp)
        editor.apply()
    }

    private fun retrieveData() {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        textView.text = getString(R.string.retrievedData,
                                    sharedPreferences.getBoolean(keyIsCharging, false).toString(),
                                    sharedPreferences.getBoolean(keyUSBCharging, false).toString(),
                                    sharedPreferences.getBoolean(keyACCharging, false).toString(),
                                    ((sharedPreferences.getFloat(keyBatteryPercentage, 0.0f))*100).toString(),
                                    (System.currentTimeMillis()/1000 -
                                            sharedPreferences.getLong(keyTimeStamp, 0)).toString())
    }
}
