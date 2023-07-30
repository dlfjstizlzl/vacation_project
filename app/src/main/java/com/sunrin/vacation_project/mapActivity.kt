package com.sunrin.vacation_project

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sunrin.vacation_project.databinding.ActivityMainBinding
import com.sunrin.vacation_project.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapView


class mapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mapView = MapView(this)
        binding.clKakaoMapView.addView(mapView)

    }
}