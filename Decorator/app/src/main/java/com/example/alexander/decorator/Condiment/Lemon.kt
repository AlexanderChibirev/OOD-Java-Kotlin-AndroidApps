package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class  Lemon(beverage: IBeverage, val quantity: Int) : CondimentDecorator(beverage)
{
    override fun GetCondimentCost() = (10.0 * quantity.toDouble())
    override fun GetCondimentDescription() = "Lemon x " + quantity.toString();
}