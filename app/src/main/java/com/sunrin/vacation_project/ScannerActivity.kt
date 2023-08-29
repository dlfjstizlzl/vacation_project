package com.sunrin.vacation_project
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

class ScannerActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val btn_exit = findViewById<ImageView>(R.id.exit_qr)
        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not
        btn_exit.setOnClickListener {
            val intent = Intent(this, mapActivity::class.java)
            startActivity(intent)
        }

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                if (it.text.toInt() == 4321) {
                    val intent = Intent(this, rent_failed::class.java) // 추후에 반납액티비티로
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                    Log.d("QrResult", it.text)
                    val intent = Intent(this, Retrofit_loadingActivity::class.java)
                    intent.putExtra("QR_value", it.text.toInt())
                    startActivity(intent)
                }
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}