package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Definitions.TeaType

/**
 * Created by Alexander on 23.06.2016.
 */
class Tea(val teaType: TeaType) : Beverage("Tea"){
    override fun GetCost() = 30.0
    override fun GetDescription(): String {
        return "${teaType.name} Tea"
    }
}