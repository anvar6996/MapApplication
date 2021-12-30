package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class EndLocationX(
    @SerializedName("lat")
    val lat: Double, // 41.3312666
    @SerializedName("lng")
    val lng: Double // 69.2703617
)