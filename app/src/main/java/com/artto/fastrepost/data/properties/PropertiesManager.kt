package com.artto.fastrepost.data.properties

import io.reactivex.subjects.BehaviorSubject

class PropertiesManager(private val propertiesDataStore: PropertiesDataStore) {

    private val propertiesBehaviorSubject = BehaviorSubject.createDefault(propertiesDataStore.load())

    val propertiesObservable = propertiesBehaviorSubject.map { it.copy() }

    val properties = propertiesBehaviorSubject.value?.copy()

    fun update(properties: Properties) {
        propertiesDataStore.save(properties)
        propertiesBehaviorSubject.onNext(properties)
    }

}