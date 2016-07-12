package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Definitions.PortionType

/**
 * Created by Alexander on 23.06.2016.
 */
class Latte(val portionType: PortionType = PortionType.Standard) : Beverage("Latte")
{
    override fun GetCost() = if (portionType == PortionType.Standard) 90.0 else 130.0
    override fun GetDescription() = "Latte ${portionType.name}"
}