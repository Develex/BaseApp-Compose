package com.develex.baseapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    fun setThemeUserSetting(value: MainViewModel.AppThemeEnum) {
        _themeUserSetting.value = value
    }

    //    The different states the dark mode can be in
    enum class AppThemeEnum {
        MODE_DAY,
        MODE_NIGHT,
        MODE_AUTO;
    }

    //    The stateflow for keeping the user setting for dark mode, by default is this automatic (follow system settings)
    private var _themeUserSetting = MutableStateFlow(AppThemeEnum.MODE_AUTO)
    val themeUserSetting: StateFlow<Enum<AppThemeEnum>> = _themeUserSetting
}

