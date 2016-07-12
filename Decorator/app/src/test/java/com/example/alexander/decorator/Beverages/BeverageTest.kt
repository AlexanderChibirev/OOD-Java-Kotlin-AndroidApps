package com.example.alexander.decorator.Beverages

import com.example.alexander.decorator.Definitions.MilkshakePortionType
import com.example.alexander.decorator.Definitions.PortionType
import com.example.alexander.decorator.Definitions.TeaType
import junit.framework.TestCase

/**
 * Created by Alexander on 23.06.2016.
 */
class BeverageTest : TestCase() {
    fun testBeverage() {
        val beverage = Beverage("beverageTest")
        assertEquals("beverageTest", beverage.GetDescription())
        assertEquals(0.0, beverage.GetCost())
    }
    fun testDefaultCappuccino() {
        val coffee = Cappuccino()
        assertEquals("Cappuccino Standard", coffee.GetDescription())
        assertEquals(80.0, coffee.GetCost())
    }

    fun testDoubleCappuccino() {
        val coffee = Cappuccino(PortionType.Double)
        assertEquals("Cappuccino Double", coffee.GetDescription())
        assertEquals(120.0, coffee.GetCost())
    }

    fun testStandardCappuccino() {
        val coffee = Cappuccino(PortionType.Standard)
        assertEquals("Cappuccino Standard", coffee.GetDescription())
        assertEquals(80.0, coffee.GetCost())
    }


    fun testDefaultCoffee() {
        val coffee = Coffee()
        assertEquals("Coffee Standard", coffee.GetDescription())
        assertEquals(60.0, coffee.GetCost())
    }

    fun testDoubleCoffee() {
        val coffee = Coffee(PortionType.Double)
        assertEquals("Coffee Double", coffee.GetDescription())
        assertEquals(120.0, coffee.GetCost())
    }

    fun testStandardCoffee() {
        val coffee = Coffee(PortionType.Standard)
        assertEquals("Coffee Standard", coffee.GetDescription())
        assertEquals(60.0, coffee.GetCost())
    }

    fun testDefaultLatte() {
        val coffee = Latte()
        assertEquals("Latte Standard", coffee.GetDescription())
        assertEquals(90.0, coffee.GetCost())
    }

    fun testDoubleLatte() {
        val coffee = Latte(PortionType.Double)
        assertEquals("Latte Double", coffee.GetDescription())
        assertEquals(130.0, coffee.GetCost())
    }

    fun testStandardLatte() {
        val coffee = Latte(PortionType.Standard)
        assertEquals("Latte Standard", coffee.GetDescription())
        assertEquals(90.0, coffee.GetCost())
    }

    fun testMilkshakeSmall() {
        val shake = Milkshake(MilkshakePortionType.Little)
        assertEquals("Milkshake Little", shake.GetDescription())
        assertEquals(50.0, shake.GetCost())
    }

    fun testMilkshakeMiddle() {
        val shake = Milkshake(MilkshakePortionType.Middle)
        assertEquals("Milkshake Middle", shake.GetDescription())
        assertEquals(60.0, shake.GetCost())
    }

    fun testMilkshakeBig() {
        val shake = Milkshake(MilkshakePortionType.Big)
        assertEquals("Milkshake Big", shake.GetDescription())
        assertEquals(80.0, shake.GetCost())
    }

    fun testBlackTea() {
        val tea = Tea(TeaType.Black)
        assertEquals("Black Tea", tea.GetDescription())
        assertEquals(30.0, tea.GetCost())
    }

    fun testGreenTea() {
        val tea = Tea(TeaType.Green)
        assertEquals("Green Tea", tea.GetDescription())
        assertEquals(30.0, tea.GetCost())
    }

    fun testYellowTea() {
        val tea = Tea(TeaType.Yellow)
        assertEquals("Yellow Tea", tea.GetDescription())
        assertEquals(30.0, tea.GetCost())
    }

    fun testFruitTea() {
        val tea = Tea(TeaType.Fruit)
        assertEquals("Fruit Tea", tea.GetDescription())
        assertEquals(30.0, tea.GetCost())
    }
}