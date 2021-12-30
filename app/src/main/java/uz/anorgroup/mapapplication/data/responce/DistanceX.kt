package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class DistanceX(
    @SerializedName("text")
    val text: String, // 0.5 km
    @SerializedName("value")
    val value: Int // 469
)