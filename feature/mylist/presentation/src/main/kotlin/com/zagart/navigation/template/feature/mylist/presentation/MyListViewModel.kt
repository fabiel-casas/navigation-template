package com.zagart.navigation.template.feature.mylist.presentation

import androidx.lifecycle.ViewModel
import com.zagart.navigation.template.presentation.navigation.MyListNavBarDestination
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegate
import com.zagart.navigation.template.presentation.navigation.NavigationEventDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor() : ViewModel(),
    NavigationEventDelegate by NavigationEventDelegateImpl()  {

    fun load(destination: MyListNavBarDestination) {
    }
}