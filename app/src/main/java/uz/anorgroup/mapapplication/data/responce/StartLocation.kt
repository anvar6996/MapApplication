package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class StartLocation(
    @SerializedName("lat")
    val lat: Double, // 41.3347116
    @SerializedName("lng")
    val lng: Double // 69.27329829999999
)