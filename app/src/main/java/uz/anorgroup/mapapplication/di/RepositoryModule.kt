package uz.anorgroup.mapapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.mapapplication.domain.MapRepository
import uz.anorgroup.mapapplication.domain.MapRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getAppRepository(impl: MapRepositoryImpl): MapRepository

}