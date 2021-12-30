package uz.anorgroup.mapapplication.data.responce

data class MapResponse(
    val routes: List<RoutesItem?>? = null,
    val geocodedWaypoints: List<GeocodedWaypointsItem?>? = null,
    val status: String? = null
)

data class RoutesItem(
    val summary: String? = null,
    val copyrights: String? = null,
    val legs: List<LegsItem?>? = null,
    val warnings: List<Any?>? = null,
    val bounds: Bounds? = null,
    val overviewPolyline: OverviewPolyline? = null,
    val waypointOrder: List<Any?>? = null
)

data class StepsItem(
    val duration: Duration? = null,
    val startLocation: StartLocation? = null,
    val distance: Distance? = null,
    val travelMode: String? = null,
    val htmlInstructions: String? = null,
    val endLocation: EndLocation? = null,
    val maneuver: String? = null,
    val polyline: Polyline? = null
)

data class Polyline(
    val points: String? = null
)

data class Duration(
    val text: String? = null,
    val value: Int? = null
)

data class EndLocation(
    val lng: Double? = null,
    val lat: Double? = null
)

data class GeocodedWaypointsItem(
    val types: List<String?>? = null,
    val geocoderStatus: String? = null,
    val placeId: String? = null
)

data class Southwest(
    val lng: Double? = null,
    val lat: Double? = null
)

data class Distance(
    val text: String? = null,
    val value: Int? = null
)

data class LegsItem(
    val duration: Duration? = null,
    val startLocation: StartLocation? = null,
    val distance: Distance? = null,
    val startAddress: String? = null,
    val endLocation: EndLocation? = null,
    val endAddress: String? = null,
    val viaWaypoint: List<Any?>? = null,
    val steps: List<StepsItem?>? = null,
    val trafficSpeedEntry: List<Any?>? = null
)

data class StartLocation(
    val lng: Double? = null,
    val lat: Double? = null
)

data class Northeast(
    val lng: Double? = null,
    val lat: Double? = null
)

data class Bounds(
    val southwest: Southwest? = null,
    val northeast: Northeast? = null
)

data class OverviewPolyline(
    val points: String? = null
)

