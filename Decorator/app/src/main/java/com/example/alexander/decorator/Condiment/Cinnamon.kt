package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class Cinnamon(beverage: IBeverage): CondimentDecorator(beverage)
{
    override fun GetCondimentCost() = 20.0
    override fun GetCondimentDescription() = "Cinnamon"
}