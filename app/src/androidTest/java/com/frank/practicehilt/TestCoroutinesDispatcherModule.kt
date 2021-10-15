package com.frank.practicehilt

import com.frank.practicehilt.di.CoroutinesDispatcherModule
import com.frank.practicehilt.di.DefaultDispatcher
import com.frank.practicehilt.di.IoDispatcher
import com.frank.practicehilt.di.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [CoroutinesDispatcherModule::class]
//)
//object TestCoroutinesDispatcherModule {
//
//    @Provides
//    @IoDispatcher
//    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.Main
//
//    @Provides
//    @MainDispatcher
//    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
//
//    @Provides
//    @DefaultDispatcher
//    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Main
//
//
//}