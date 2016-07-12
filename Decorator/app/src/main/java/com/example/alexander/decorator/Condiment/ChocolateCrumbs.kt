package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class ChocolateCrumbs(beverage: IBeverage, val mass: Int) : CondimentDecorator(beverage)
{
    override fun GetCondimentDescription() = "Chocolate crumbs $mass g"
    override fun GetCondimentCost() = 2.0 * mass
}