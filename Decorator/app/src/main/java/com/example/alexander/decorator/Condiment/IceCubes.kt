package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Definitions.IceCubeType
import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */

class IceCubes(beverage: IBeverage, val quantity: Int, val type: IceCubeType = IceCubeType.Water) : CondimentDecorator(beverage)
{
    override fun GetCondimentDescription() = "${type.name} ice cubes x $quantity"
    override fun GetCondimentCost() = if(type == IceCubeType.Dry) 10.0 * quantity else 5.0 * quantity
}