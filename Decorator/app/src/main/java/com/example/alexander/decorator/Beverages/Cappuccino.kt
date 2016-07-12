package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Definitions.PortionType

/**
 * Created by Alexander on 23.06.2016.
 */
class Cappuccino(portionType: PortionType = PortionType.Standard): Coffee(portionType)//определили стандарт для дефолта
{
    override fun GetCost() = if (portionType == PortionType.Standard) 80.0 else 120.0
    override fun GetDescription() = "Cappuccino ${portionType.name}"
}