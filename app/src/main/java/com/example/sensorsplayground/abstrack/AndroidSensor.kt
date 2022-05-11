package com.example.sensorsplayground.abstrack

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.pm.PackageManager
import android.hardware.*

class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    private val sensorDelay: Int = SensorManager.SENSOR_DELAY_GAME,
    sensorType: Int
): SensorEventListener {
    private val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    var onSensorValueChanged: ((FloatArray?) -> Unit)? = null
    private val sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
    private val sensor: Sensor? = if (doesSensorExist) sensorManager.getDefaultSensor(sensorType) else null

    fun startListen() {
        if (doesSensorExist)
            sensorManager.registerListener(this, sensor, sensorDelay)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            onSensorValueChanged?.invoke(it.values)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}