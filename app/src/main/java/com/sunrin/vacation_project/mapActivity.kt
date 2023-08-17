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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        val marker = MapPOIItem()
        marker.apply {
            itemName = "서울시청"   // 마커 이름
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5666805, 126.9784147)   // 좌표
            markerType = MapPOIItem.MarkerType.CustomImage          // 마커 모양 (커스텀)
            customImageResourceId = R.drawable.custom_marker               // 커스텀 마커 이미지
            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  // 클릭 시 마커 모양 (커스텀)
            customSelectedImageResourceId = R.drawable.custom_marker2       // 클릭 시 커스텀 마커 이미지
            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
            setCustomImageAnchor(0.5f, 1.0f)    // 마커 이미지 기준점
        }
        mapView.addPOIItem(marker)
        //레트로핏2 통신
        RetrofitClass.getInstance()?.getUserPage(1234)?.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성고된 경우
                    var result: User? = response.body()
                    Log.d("YMC", "onResponse2 성공: " + result.toString());
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("YMC", "onResponse2 실패")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("YMC", "onFailure2 에러: " + t.message.toString());
            }
        })
    }
}