package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class GeocodedWaypoint(
    @SerializedName("geocoder_status")
    val geocoderStatus: String, // OK
    @SerializedName("place_id")
    val placeId: String, // ChIJYefS1FqLrjgRLAlDJWAi3EQ
    @SerializedName("types")
    val types: List<String>
)