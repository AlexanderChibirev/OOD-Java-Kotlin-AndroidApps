package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
open class  Cream(beverage: IBeverage) : CondimentDecorator(beverage)
{
    override fun GetCondimentCost() = 25.0
    override fun GetCondimentDescription() = "Cream";
}