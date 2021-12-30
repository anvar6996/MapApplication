package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("distance")
    val distance: DistanceX,
    @SerializedName("duration")
    val duration: DurationX,
    @SerializedName("end_location")
    val endLocation: EndLocationX,
    @SerializedName("html_instructions")
    val htmlInstructions: String, // Head <b>southwest</b> on <b>Xurshid ko'chasi</b> toward <b>Nurafshon ko'chasi</b>/<wbr/><b>Бахор кўчаси</b>
    @SerializedName("maneuver")
    val maneuver: String, // turn-left
    @SerializedName("polyline")
    val polyline: Polyline,
    @SerializedName("start_location")
    val startLocation: StartLocationX,
    @SerializedName("travel_mode")
    val travelMode: String // DRIVING
)