package com.develex.baseapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    //    The different states the dark mode can be in
    enum class AppThemeEnum {
        MODE_DAY,
        MODE_NIGHT,
        MODE_AUTO;
    }

    //    The stateflow for keeping the user setting for dark mode, by default is this automatic (follow system settings)
    private val _themeUserSetting = MutableStateFlow(AppThemeEnum.MODE_AUTO)
    val themeUserSetting: StateFlow<Enum<AppThemeEnum>> = _themeUserSetting

    fun setThemeUserSetting(value: Enum<AppThemeEnum>){
        _themeUserSetting.value = value as AppThemeEnum
    }
}
