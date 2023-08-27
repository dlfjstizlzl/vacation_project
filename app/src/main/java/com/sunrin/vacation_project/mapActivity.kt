package com.sunrin.vacation_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.auth.User
import com.sunrin.vacation_project.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class mapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding    // 뷰 바인딩
    private lateinit var mapView : MapView              // 카카오 지도 뷰

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mapView = MapView(this)
        binding.clKakaoMapView.addView(mapView)

        binding.qrbutton.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }
        binding.ivNotification.setOnClickListener {
        }
        binding.ivMenu.setOnClickListener {
            val intent = Intent(this, profileActivity::class.java)
            startActivity(intent)
        }
        val marker = MapPOIItem()
        marker.apply {
            itemName = "선린인터넷고등학교"   // 마커 이름
            mapPoint = MapPoint.mapPointWithGeoCoord(37.54315699940157, 126.96766036490028)   // 좌표
            markerType = MapPOIItem.MarkerType.CustomImage          // 마커 모양 (커스텀)
            customImageResourceId = R.drawable.custom_marker          // 커스텀 마커 이미지
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  // 클릭 시 마커 모양 (커스텀)
            customSelectedImageResourceId = R.drawable.custom_marker   // 클릭 시 커스텀 마커 이미지
            isCustomImageAutoscale = true      // 커스텀 마커 이미지 크기 자동 조정
        }
        mapView.addPOIItem(marker)
    }
}