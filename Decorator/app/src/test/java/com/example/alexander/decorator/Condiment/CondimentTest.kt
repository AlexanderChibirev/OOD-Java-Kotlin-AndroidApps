package com.example.alexander.decorator.Condiment

import com.example.alexander.decorator.Beverages.Beverage
import com.example.alexander.decorator.Beverages.Coffee
import com.example.alexander.decorator.Definitions.IceCubeType
import com.example.alexander.decorator.Definitions.LiquorType
import com.example.alexander.decorator.Definitions.NumberOfChocolatePieces
import com.example.alexander.decorator.Definitions.SyrupType
import com.example.alexander.decorator.Interfaces.IBeverage
import junit.framework.TestCase

/**
 * Created by Alexander on 23.06.2016.
 */
class CondimentTest : TestCase() {

    val coffeePrice = 60.0
    //то что в класее, а потом ->  Expected - ожидаемое
    fun assertBeverage(beverage: IBeverage, description: String, price: Double) {
        assertEquals(description, beverage.GetDescription())
        assertEquals(price, beverage.GetCost())
    }

    fun testCinnamonOnBeverage() {
        assertBeverage(Cinnamon(Beverage("drink")), "drink, Cinnamon", 20.0)
    }

    fun testCinnamonOnCoffee() {
        assertBeverage(Cinnamon(Coffee()), "Coffee Standard, Cinnamon", coffeePrice + 20.0)
    }

    fun testLemonOnBeverage() {
        assertBeverage(Lemon(Beverage("drink"), 1), "drink, Lemon x 1", 10.0)
        assertBeverage(Lemon(Beverage("drink"), 2), "drink, Lemon x 2", 20.0)
    }

    fun testLemonOnCoffee() {
        assertBeverage(Lemon(Coffee(), 2), "Coffee Standard, Lemon x 2", coffeePrice + 20.0)
    }

    fun testCoconutFlakesOnBeverage() {
        assertBeverage(CoconutFlakes(Beverage("drink"), 5), "drink, Coconut flakes 5g", 5.0)
    }

    fun testCoconutFlakesOnCoffee() {
        assertBeverage(CoconutFlakes(Coffee(), 2), "Coffee Standard, Coconut flakes 2g", coffeePrice + 2.0)
    }

    fun testChocolateCrumbsOnCoffee() {
        assertBeverage(ChocolateCrumbs(Coffee(), 2), "Coffee Standard, Chocolate crumbs 2 g", coffeePrice + 4.0)
    }

    fun testSyrupOnCoffee() {
        assertBeverage(Syrup(Coffee(), SyrupType.Chocolate), "Coffee Standard, Chocolate syrup", coffeePrice + 15.0)
        assertBeverage(Syrup(Coffee(), SyrupType.Maple), "Coffee Standard, Maple syrup", coffeePrice + 15.0)
    }

    fun testIceCubesOnCoffee() {
        assertBeverage(IceCubes(Coffee(), 2), "Coffee Standard, Water ice cubes x 2", coffeePrice + 2*5.0)
        assertBeverage(IceCubes(Coffee(), 2, IceCubeType.Water), "Coffee Standard, Water ice cubes x 2", coffeePrice + 2*5.0)
        assertBeverage(IceCubes(Coffee(), 2, IceCubeType.Dry), "Coffee Standard, Dry ice cubes x 2", coffeePrice + 2*10.0)
    }

    fun testCreamOnCoffee() {
        assertBeverage(Cream(Coffee()), "Coffee Standard, Cream", coffeePrice + 25.0)
    }

    fun testLiquorOnCoffee() {
        assertBeverage(Liquor(Coffee(), LiquorType.Chocolate), "Coffee Standard, Chocolate liquor", coffeePrice + 15.0)
        assertBeverage(Liquor(Coffee(), LiquorType.Walnut), "Coffee Standard, Walnut liquor", coffeePrice + 15.0)
    }

    fun testChocolateOnCoffee() {
        assertBeverage(Chocolate(Coffee(), NumberOfChocolatePieces.One), "Coffee Standard, Chocolate Slice 1", coffeePrice + 10.0)
        assertBeverage(Chocolate(Coffee(), NumberOfChocolatePieces.Five), "Coffee Standard, Chocolate Slice 5", coffeePrice + 5*10.0)
    }
}