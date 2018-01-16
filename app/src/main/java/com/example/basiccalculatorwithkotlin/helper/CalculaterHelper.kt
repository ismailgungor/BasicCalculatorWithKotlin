package com.example.basiccalculatorwithkotlin.helper

import android.widget.Button
import com.example.basiccalculatorwithkotlin.util.OperationMapKeys

/**
 * Created by ismailgungor on 16.01.2018.
 */
class CalculaterHelper(textAreaHelper: TextAreaHelper) {

    private var mTextAreaHelper: TextAreaHelper = textAreaHelper


    fun getChangedTextAreaValue(button: Button, currentValue: String): String {

        var currentText = currentValue

        if (isWritable(currentValue))
            currentText = mTextAreaHelper.addValue(currentValue, button.text.toString())

        return currentText
    }

    fun getChangedPlusMinusValue(currentValue: String): String {

        var currentText = currentValue

        if (currentText.isNotEmpty())
            currentText = mTextAreaHelper.makeOpposite(currentValue)

        return currentText
    }

    fun getPutDotValue(currentValue: String): String {

        var currentText = currentValue
        if (isWritable(currentValue))
            currentText = mTextAreaHelper.putDot(currentValue)

        return currentText

    }

    fun getDeletedText(currentValue: String): String {

        if (currentValue.isNotBlank() && !currentValue.equals("0")) {

            if (currentValue.length == 1) {
                return "0"
            } else {
                return this.mTextAreaHelper.deleteDigit(currentValue)
            }

        }

        return currentValue

    }

    fun getOperationMap(button: Button, currentValue: String): HashMap<String, String> {

        val operationMap = HashMap<String, String>()

        val buttonText = button.text.toString()
        operationMap.put(OperationMapKeys.KEY_TYPE, buttonText)
        operationMap.put(OperationMapKeys.KEY_AREA_TEXT, currentValue + " " + buttonText)

        return operationMap
    }

    private fun isWritable(currentValue: String): Boolean {

        val textSize = this.mTextAreaHelper.getTextSize(currentValue)

        if (textSize < 16)
            return true


        return false
    }

    fun removeUnnecessaryDigits(temporaryValue: String): String {

        if (temporaryValue.contains(".")) {
            val textSize = temporaryValue.length
            val dotIndex = temporaryValue.indexOf(".")
            val difference = textSize - dotIndex

            // Remove dot digits if last digit is zero
            if (difference == 2 && temporaryValue[textSize - 1] == '0') {
                return temporaryValue.substring(0, dotIndex)
            }

        }

        return temporaryValue
    }


}