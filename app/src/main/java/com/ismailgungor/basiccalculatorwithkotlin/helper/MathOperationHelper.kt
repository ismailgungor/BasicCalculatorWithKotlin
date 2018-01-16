package com.ismailgungor.basiccalculatorwithkotlin.helper

/**
 * Created by ismailgungor on 16.01.2018.
 */
class MathOperationHelper {

    fun calculateByAdding(currentDouble: Double, newDouble: Double): String {

        return (currentDouble + newDouble).toString()
    }

    fun calculateBySubtracting(currentDouble: Double, newDouble: Double): String {
        return (currentDouble - newDouble).toString()
    }

    fun calculateByMultiplying(currentDouble: Double, newDouble: Double): String {
        return (currentDouble * newDouble).toString()
    }

    fun calculateByDividing(currentDouble: Double, newDouble: Double): String {
        if (newDouble != 0.0) {
            return (currentDouble / newDouble).toString()
        }
        return "ZeroDivideError"
    }

    fun calculateByTakingPercent(currentDouble: Double): String {
        return (currentDouble / 100).toString()
    }

}