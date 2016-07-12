package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
open class Beverage(val description: String) : IBeverage
{
    override fun GetDescription() = description
    override fun GetCost() = 0.0
}