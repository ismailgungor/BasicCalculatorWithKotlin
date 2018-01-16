package com.example.basiccalculatorwithkotlin

import dagger.Component
import javax.inject.Singleton

/**
 * Created by ismailgungor on 16.01.2018.
 */
@Singleton
@Component(modules = arrayOf(CalculatorActivityModule::class))
interface CalculatorActivityComponent {

    fun inject(calculatorActivity: CalculatorActivity)

}