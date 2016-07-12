package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Definitions.LiquorType
import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class Liquor(beverage :IBeverage,val liquorType: LiquorType) : CondimentDecorator(beverage)
{
    override fun GetCondimentCost() = 15.0
    override fun GetCondimentDescription() = "${liquorType.name} liquor"
}