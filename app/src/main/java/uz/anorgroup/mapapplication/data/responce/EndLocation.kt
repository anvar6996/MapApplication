package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class EndLocation(
    @SerializedName("lat")
    val lat: Double, // 41.27925949999999
    @SerializedName("lng")
    val lng: Double // 69.2170972
)