package com.example.basiccalculatorwithkotlin

import com.example.basiccalculatorwithkotlin.helper.CalculaterHelper
import com.example.basiccalculatorwithkotlin.helper.MathOperationHelper
import com.example.basiccalculatorwithkotlin.helper.TextAreaHelper
import dagger.Module
import dagger.Provides

/**
 * Created by ismailgungor on 16.01.2018.
 */
@Module
class CalculatorActivityModule {

    @Provides
    fun provideTextAreaHelper(): TextAreaHelper {
        return TextAreaHelper()
    }

    @Provides
    fun provideChoosedButtonHelper(textAreaHelper: TextAreaHelper): CalculaterHelper {

        return CalculaterHelper(textAreaHelper)
    }

    @Provides
    fun provideMathOperationHelper(): MathOperationHelper {
        return MathOperationHelper()
    }

}