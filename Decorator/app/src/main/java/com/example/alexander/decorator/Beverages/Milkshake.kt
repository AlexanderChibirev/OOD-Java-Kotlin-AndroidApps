package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Definitions.MilkshakePortionType

/**
 * Created by Alexander on 23.06.2016.
 */
class Milkshake(val portionType: MilkshakePortionType) : Beverage("Milkshake"){
    override fun GetCost():Double {
        when(portionType)
        {
            MilkshakePortionType.Little -> return 50.0
            MilkshakePortionType.Big -> return 80.0
            MilkshakePortionType.Middle -> return 60.0
        }
    }
    override fun GetDescription(): String {
        return "Milkshake ${portionType.name}"
    }
}