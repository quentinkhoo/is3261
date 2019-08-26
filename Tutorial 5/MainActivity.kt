package com.helloworld.sylvia.tutorial5

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.icu.lang.UProperty.AGE
import android.os.BatteryManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private var myPreferences = "myPrefs"
    private var EMPTY = ""
    private var IS_CHARGING = "isCharging"
    private var AC_CHARGING = "acCharging"
    private var USB_CHARGING = "usbCharging"
    private var BATT_PCT = "batteryPercentage"
    private var SAVEDTIME  = "timestamp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)

        val btnSave = findViewById<Button>(R.id.statusBtn)
        btnSave.setOnClickListener{
            saveData()
        }

        val btnRetrieve = findViewById<Button>(R.id.retrieveBtn)
        btnSave.setOnClickListener{
            retrieveData()
        }


    }


    fun saveData(){
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
            ifilter -> this.registerReceiver(null, ifilter)
        }

        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)?: -1

        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)?:-1

        val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

        val batteryPct: Float = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level / scale.toFloat()
        }?: -1.0f
        var time = System.currentTimeMillis()
        time = time/1000;


        val editor = sharedPreferences.edit()

        editor.putBoolean(IS_CHARGING, isCharging)
        editor.putBoolean(USB_CHARGING, usbCharge)
        editor.putBoolean(AC_CHARGING, acCharge)
        editor.putFloat(BATT_PCT, batteryPct)
        editor.putLong(SAVEDTIME, time)
        editor.apply()
    }

    fun retrieveData(){

        val isCharging: Boolean = sharedPreferences.getBoolean(IS_CHARGING, false)
        val usbCharge: Boolean = sharedPreferences.getBoolean(USB_CHARGING, false)
        val acCharge: Boolean = sharedPreferences.getBoolean(AC_CHARGING, false)
        val batteryPct: Float = sharedPreferences.getFloat(BATT_PCT, 0.0f )
        val time: Long = sharedPreferences.getLong(SAVEDTIME, 0)

        var string: String = "The stored sharedPreferences data are: \n\n Is Charging = " + isCharging.toString() + "\n\n" +
                "USB Charging = " + usbCharge.toString() + "\n\n" + "AC Charging = " + acCharge.toString() + "\n\n" +
                "Battery Percentage = " + batteryPct.toString() + "\n\n" + "Saved " +
                ((System.currentTimeMillis()/1000)- time).toString() + " seconds ago "

        var textView : TextView = findViewById<TextView>(R.id.textView)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        textView.setText(string)


//        textView.text = getString(R.string.retrievedData ,sharedPreferences.getBoolean(IS_CHARGING, false).toString(),
//                sharedPreferences.getBoolean(USB_CHARGING, false).toString(),
//                sharedPreferences.getBoolean(AC_CHARGING, false).toString(),
//                ((sharedPreferences.getFloat(BATT_PCT, 0.0f))*100).toString(),
//                (System.currentTimeMillis()/1000 -
//                        sharedPreferences.getLong(SAVEDTIME, 0)).toString())


    }

}
