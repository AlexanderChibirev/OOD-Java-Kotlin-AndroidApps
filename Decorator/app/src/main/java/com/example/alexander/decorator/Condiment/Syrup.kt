package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Definitions.SyrupType
import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class Syrup(beverage: IBeverage, val type: SyrupType) : CondimentDecorator(beverage)
{
    override fun GetCondimentDescription() = "${type.name} syrup"
    override fun GetCondimentCost() = 15.0
}