package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class Duration(
    @SerializedName("text")
    val text: String, // 17 mins
    @SerializedName("value")
    val value: Int // 1048
)