package com.example.sensorsplayground.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.sensorsplayground.abstrack.AndroidSensor

class MainViewModel(context: Application): AndroidViewModel(context) {
    private val magneticSensor = AndroidSensor(
        context = context,
        sensorFeature = PackageManager.FEATURE_SENSOR_COMPASS,
        sensorType = Sensor.TYPE_MAGNETIC_FIELD,
        sensorDelay = SensorManager.SENSOR_DELAY_NORMAL
    )
    var magneticStrength by mutableStateOf(0f)

    init {
        magneticSensor.startListen()
        magneticSensor.onSensorValueChanged = {
            it?.also {
                magneticStrength = it[0]
            }
        }
    }
}