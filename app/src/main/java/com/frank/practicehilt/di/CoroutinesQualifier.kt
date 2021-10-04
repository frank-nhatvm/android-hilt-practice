package com.frank.practicehilt.di

import javax.inject.Qualifier


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class  DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class  IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class  MainDispatcher

