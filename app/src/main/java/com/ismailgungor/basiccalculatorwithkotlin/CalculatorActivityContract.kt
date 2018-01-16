package com.ismailgungor.basiccalculatorwithkotlin

import android.widget.Button

/**
 * Created by ismailgungor on 16.01.2018.
 */
interface CalculatorActivityContract {

    interface View {


        fun setTextArea(currentText: String)

        fun setHistoryTextArea(history: String)

    }

    interface Presenter {

        fun setView(view: View)

        fun changeTextAreaValue(button: Button, currentValue: String)

        fun changePlusMinus(currentValue: String)

        fun putDot(currentValue: String)

        fun deleteNumber(currentValue: String)

        fun startOperationProcess(button: Button, currentValue: String)

        fun calculateResult(currentValue: String)

        fun startPercentageProcess(currentValue: String)

        fun restartProcess()


    }

}