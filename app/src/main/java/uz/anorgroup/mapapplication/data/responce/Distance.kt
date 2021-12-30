package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class Distance(
    @SerializedName("text")
    val text: String, // 10.8 km
    @SerializedName("value")
    val value: Int // 10840
)