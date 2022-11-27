package com.example.additional

import android.content.Context
import com.example.rawgamesdb.features.di.FavModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavModuleDependencies::class])
interface FavComponent {

    fun inject(favouriteActivity: FavouriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDeps(favModuleDependencies: FavModuleDependencies): Builder
        fun build(): FavComponent
    }
}