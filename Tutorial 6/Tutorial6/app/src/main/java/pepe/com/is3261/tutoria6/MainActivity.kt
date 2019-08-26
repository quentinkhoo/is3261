package pepe.com.is3261.tutoria6

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val permissionList = arrayOf(
            Manifest.permission.CAMERA)

    lateinit var cameraView : SurfaceView
    lateinit var message: TextView
    lateinit var url: TextView

    lateinit var barcodeDetector: BarcodeDetector

    lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraView = findViewById<SurfaceView>(R.id.cameraView)
        message = findViewById<TextView>(R.id.message)
        url = findViewById<TextView>(R.id.url)

        barcodeDetector = BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cameraPermissionEnabled()) {
                setUpCamera()
            } else {
                setupCameraPermission()
            }
        } else {
            // it must be older than Marshmallow, no need to do anything as long as
            // you have added the permission in the AndroidManifest.xml file
        }

        readQRCode()
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun cameraPermissionEnabled(): Boolean {
        return permissionList.all {
            checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun setupCameraPermission() {
        val permissionsNotGranted = permissionList.filter {
            checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
        }
        requestPermissions(permissionsNotGranted.toTypedArray(), 101)
    }

    private fun readQRCode() {
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {

            var qrCodeText: String = ""
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                var barcodes = detections.detectedItems

                if (barcodes.size() != 0) {
                    url.post{
                        qrCodeText = barcodes.valueAt(0).displayValue
                        url.text = qrCodeText
                        InternetJSON(this@MainActivity, qrCodeText, message).execute()
                    }

                }
            }
        })
    }

    private fun setUpCamera() {
        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    cameraSource.start(cameraView.holder)

                } catch (ie: IOException) {
                    Log.e("Camera source", ie.message)
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder,
                                        format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissionsList: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults)

        if (requestCode == 101) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED}) {

                val myIntent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(myIntent)
            }
        }

    }

}
