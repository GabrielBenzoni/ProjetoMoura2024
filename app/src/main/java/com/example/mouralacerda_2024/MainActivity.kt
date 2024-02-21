package com.example.mouralacerda_2024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(),SensorEventListener, LocationListener {
    var mSensorManager: SensorManager? = null
    var mLocationManager: LocationManager? = null

    var txtLatitude: TextView? = null
    var txtLongetude: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            Toast.makeText(this,"Permita a localização", Toast.LENGTH_LONG).show()
            //Peça A Permissao de localização
            return
        }
        
        txtLatitude = findViewById<TextView>(R.id.txtLatitude)
        txtLongetude = findViewById<TextView>(R.id.txtLongetude1)

        val button1 = findViewById<Button>(R.id.btt_localizacao)
        button1.setOnClickListener { mLocationManager!!.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 1000, 0f,this) }

    }

    override fun onLocationChanged(p0: Location) {
        txtLatitude!!.text = p0.latitude.toString()
        txtLongetude!!.text = p0.longitude.toString()
    }
}