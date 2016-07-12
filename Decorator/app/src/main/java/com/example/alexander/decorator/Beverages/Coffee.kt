package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Definitions.PortionType

/**
 * Created by Alexander on 23.06.2016.
 */
open class Coffee constructor(val portionType: PortionType = PortionType.Standard) : Beverage("Coffee")//возможно тут нужно будет вар
{
    override fun GetCost() = if (portionType == PortionType.Standard) 60.0 else 120.0
    override fun GetDescription() = "Coffee ${portionType.name}"
}