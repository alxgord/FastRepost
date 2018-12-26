package com.artto.fastrepost.ui.dialog.rate

import com.artto.fastrepost.data.properties.PropertiesManager

class RateInteract(private val propertiesManager: PropertiesManager) {

    fun setRatedApp(isRated: Boolean) = propertiesManager.properties?.let {
        propertiesManager.update(it.apply { isAppRated = isRated })
    }

}