package uz.anorgroup.mapapplication.viewmodel

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import uz.anorgroup.mapapplication.data.responce.MapResponse

interface MapViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successFlow: Flow<List<LatLng>>
    val openAddCarFlow: Flow<Unit>
    val openCreateOrderFlow: Flow<Unit>
    fun getMap(
        origin: String,
        destination: String,
        key: String
    )
}