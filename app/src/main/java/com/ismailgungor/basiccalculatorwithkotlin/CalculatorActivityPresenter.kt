package com.ismailgungor.basiccalculatorwithkotlin

import android.widget.Button
import com.ismailgungor.basiccalculatorwithkotlin.helper.CalculaterHelper
import com.ismailgungor.basiccalculatorwithkotlin.helper.MathOperationHelper
import com.ismailgungor.basiccalculatorwithkotlin.util.OperationMapKeys
import javax.inject.Inject

/**
 * Created by ismailgungor on 16.01.2018.
 */
class CalculatorActivityPresenter @Inject constructor(calculaterHelper: CalculaterHelper, mathOperationHelper: MathOperationHelper) : CalculatorActivityContract.Presenter {

    private lateinit var mView: CalculatorActivityContract.View
    private var mCalculaterHelper: CalculaterHelper = calculaterHelper
    private var mMathOperaionHelper: MathOperationHelper = mathOperationHelper
    private var isOperationContinue = false
    private var operationType = ""
    private var historyValue = ""
    private var resultValue = ""
    private var isNewText = false


    override fun setView(view: CalculatorActivityContract.View) {
        this.mView = view
    }

    override fun changeTextAreaValue(button: Button, currentValue: String) {

        val currentText: String

        if (isNewText) {
            currentText = this.mCalculaterHelper.getChangedTextAreaValue(button, "")
            isNewText = false
        } else {
            currentText = this.mCalculaterHelper.getChangedTextAreaValue(button, currentValue)

        }

        mView.setTextArea(currentText)

    }

    override fun changePlusMinus(currentValue: String) {

        val currentText = this.mCalculaterHelper.getChangedPlusMinusValue(currentValue)
        mView.setTextArea(currentText)

    }

    override fun putDot(currentValue: String) {
        val currentText = this.mCalculaterHelper.getPutDotValue(currentValue)
        mView.setTextArea(currentText)
    }

    override fun deleteNumber(currentValue: String) {

        val currentText = this.mCalculaterHelper.getDeletedText(currentValue)
        mView.setTextArea(currentText)

    }

    override fun startOperationProcess(button: Button, currentValue: String) {

        if (!isOperationContinue) {

            isOperationContinue = true
            historyValue = currentValue
            val operationMap = this.mCalculaterHelper.getOperationMap(button, historyValue)

            operationMap.get(OperationMapKeys.KEY_TYPE)?.let { this.operationType = (it) }
            operationMap.get(OperationMapKeys.KEY_AREA_TEXT)?.let { mView.setHistoryTextArea(it) }
            mView.setTextArea("")

        }
    }

    override fun calculateResult(currentValue: String) {

        if (isOperationContinue && currentValue.isNotEmpty() && !currentValue.equals(".")) {

            isOperationContinue = false
            isNewText = true
            var temporaryResult = ""

            mView.setHistoryTextArea("")

            when (operationType) {

                "+" -> temporaryResult = mMathOperaionHelper.calculateByAdding(historyValue.toDouble(), currentValue.toDouble())
                "-" -> temporaryResult = mMathOperaionHelper.calculateBySubtracting(historyValue.toDouble(), currentValue.toDouble())
                "X" -> temporaryResult = mMathOperaionHelper.calculateByMultiplying(historyValue.toDouble(), currentValue.toDouble())
                "/" -> temporaryResult = mMathOperaionHelper.calculateByDividing(historyValue.toDouble(), currentValue.toDouble())

            }

            resultValue = mCalculaterHelper.removeUnnecessaryDigits(temporaryResult)
            mView.setTextArea(resultValue)

        }

    }

    override fun startPercentageProcess(currentValue: String) {

        if (!isOperationContinue) {

            isNewText = true

            resultValue = mCalculaterHelper.removeUnnecessaryDigits(mMathOperaionHelper.calculateByTakingPercent(currentValue.toDouble()))
            mView.setTextArea(resultValue)
        }


    }

    override fun restartProcess() {

        isOperationContinue = false
        isNewText = false
        resultValue = "0"
        historyValue = ""
        operationType = ""
        mView.setTextArea("0")
        mView.setHistoryTextArea("")


    }
}