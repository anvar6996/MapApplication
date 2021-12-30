package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class DurationX(
    @SerializedName("text")
    val text: String, // 2 mins
    @SerializedName("value")
    val value: Int // 107
)