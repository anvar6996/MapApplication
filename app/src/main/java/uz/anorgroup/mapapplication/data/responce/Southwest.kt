package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class Southwest(
    @SerializedName("lat")
    val lat: Double, // 41.2788539
    @SerializedName("lng")
    val lng: Double // 69.2170972
)