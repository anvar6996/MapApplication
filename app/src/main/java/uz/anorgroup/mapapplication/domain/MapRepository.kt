package uz.anorgroup.mapapplication.domain

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.mapapplication.data.responce.MapResponse

interface MapRepository {
    fun getDirection(
        origin: String,
        destination: String,
        key: String,
    ): Flow<Result<MapResponse>>
}