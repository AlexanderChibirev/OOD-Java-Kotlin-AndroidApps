package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Definitions.NumberOfChocolatePieces
import com.example.alexander.decorator.Interfaces.IBeverage

/**
 * Created by Alexander on 23.06.2016.
 */
class Chocolate(beverage: IBeverage, val pieces: NumberOfChocolatePieces) : CondimentDecorator(beverage)
{
    override fun GetCondimentCost() = 10.0 * (pieces.ordinal + 1)//под вопросом
    override fun GetCondimentDescription() = "Chocolate Slice ${pieces.ordinal + 1}"
}
