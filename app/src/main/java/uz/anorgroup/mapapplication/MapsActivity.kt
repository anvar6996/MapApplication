package uz.anorgroup.mapapplication

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.json.JSONException
import org.json.JSONObject
import uz.anorgroup.mapapplication.databinding.ActivityMapsBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val listPoints = ArrayList<LatLng>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        setUpMap()
        map.setOnMapLongClickListener {
            if (listPoints.size == 2) {
                listPoints.clear()
                map.clear()
            }
            listPoints.add(it)
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            if (listPoints.size == 1) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            } else {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
            map.addMarker(markerOptions)
            if (listPoints.size == 2) {
                val url = getRequestUrl(listPoints[0], listPoints[1])
                val taskRequest = TaskRequestDirection()
                taskRequest.execute(url)
            }
        }
    }

    private fun getRequestUrl(origing: LatLng, dest: LatLng): String {
        val str_org = "origin=" + origing.latitude + "," + origing.longitude
        val str_dest = "destination=" + dest.latitude + "," + dest.longitude
        val sensor = "sensor=false"
        val mode = "modeDriving"
        val param = "$str_org&$str_dest&$sensor&$mode"
        val output = "json"
        val url = "https://maps.googleapis.com/maps/api/directions/$output?$param"
        return url
    }

    private fun requestDirection(reqUrl: String): String {
        var responce: String = ""
        var inputStream: InputStream? = null
        var httptUrlConnection: HttpURLConnection? = null
        try {
            val url: URL = URL(reqUrl)
            httptUrlConnection = url.openConnection() as HttpURLConnection?
            httptUrlConnection?.connect()
            inputStream = httptUrlConnection?.inputStream
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferReader = BufferedReader(inputStreamReader)
            val stringBuffer = StringBuffer()
            val line = ""
            while ((line == bufferReader.readLine()!!)) {
                stringBuffer.append(line)
            }
            responce = stringBuffer.toString()
            bufferReader.close()
            inputStream?.close()

        } catch (exp: Exception) {
            exp.printStackTrace()
        } finally {
            inputStream?.close()
            httptUrlConnection?.disconnect()
        }
        return responce
    }


    private fun setUpMap() {
        val granted = PackageManager.PERMISSION_GRANTED
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != granted &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != granted
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                0
            )
            map.isMyLocationEnabled = true
            return
        }

        task.addOnSuccessListener {
//            val yourCoordinate = LatLng(it.latitude, it.longitude)
            val destanation = LatLng(41.26465, 69.21627)
//            map.addMarker(MarkerOptions().position(yourCoordinate).title("You"))
            map.addMarker(MarkerOptions().position(destanation).title("Amir Temur"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(destanation, 15f))
        }

    }

    @SuppressLint("StaticFieldLeak")
    inner class TaskRequestDirection : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            var str = ""
            try {
                str = requestDirection(params[0]!!)
            } catch (exp: IOException) {
                exp.printStackTrace()
            }
            return str
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val taskParser = TaskParser()
            taskParser.execute(result)
        }
    }

    @SuppressLint("StaticFieldLeak")
    open inner class TaskParser : AsyncTask<String, Void, List<List<HashMap<String, String>>>>() {

        override fun doInBackground(vararg params: String?): List<List<HashMap<String, String>>>? {
            var jsonObj: JSONObject? = null
            var routes: List<List<HashMap<String, String>>>? = null
            try {
                jsonObj = JSONObject(params[0])
                val directionsParser = DirectionsJSONParser()
                routes = directionsParser.parse(jsonObj)
            } catch (exp: JSONException) {
                exp.printStackTrace()
            }
            return routes
        }

        override fun onPostExecute(result: List<List<HashMap<String, String>>>?) {
            var points: ArrayList<LatLng>? = null
            var polylineOptions: PolylineOptions? = null
            if (result != null) {
                for (path in result) {
                    points = ArrayList()
                    polylineOptions = PolylineOptions()
                    for (point in path) {
                        val lat = point["lat"]!!.toDouble()
                        val lon = point["lon"]!!.toDouble()
                        points.addAll(listOf(LatLng(lat, lon)))
                    }
                    polylineOptions.addAll(points)
                    polylineOptions.width(15f)
                    polylineOptions.color(Color.BLUE)
                    polylineOptions.geodesic(true)
                }
            }
            if (polylineOptions != null) {
                map.addPolyline(polylineOptions)
            } else {
                Toast.makeText(this@MapsActivity, "Directions not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
