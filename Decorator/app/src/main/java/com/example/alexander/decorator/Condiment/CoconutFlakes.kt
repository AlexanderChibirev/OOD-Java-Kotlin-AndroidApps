package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class CoconutFlakes(beverage: IBeverage,val mass : Int) : CondimentDecorator(beverage)
{
    override fun GetCondimentCost() = 1.0 * mass.toDouble()
    override fun GetCondimentDescription() =  "Coconut flakes " + mass.toString() + "g";
}