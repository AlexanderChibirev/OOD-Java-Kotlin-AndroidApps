package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Interfaces.IBeverage
/**
 * Created by Alexander on 23.06.2016.
 */

abstract class CondimentDecorator(val beverage: IBeverage) : IBeverage
{
    abstract protected fun GetCondimentDescription(): String
    abstract protected fun GetCondimentCost(): Double

    override fun GetDescription() = beverage.GetDescription() + ", " + GetCondimentDescription()
    override fun GetCost() = beverage.GetCost() + GetCondimentCost()
}