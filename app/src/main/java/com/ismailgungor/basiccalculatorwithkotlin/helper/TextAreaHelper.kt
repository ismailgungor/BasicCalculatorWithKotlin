package com.ismailgungor.basiccalculatorwithkotlin.helper

/**
 * Created by ismailgungor on 16.01.2018.
 */
class TextAreaHelper {

    fun getTextSize(currentText: String): Int {

        val textSize = currentText.length
        if (currentText.contains(".")) {
            return textSize - 1
        }

        return textSize

    }

    fun addValue(currentText: String, value: String): String {

        if (currentText.equals("0"))
            return value

        return currentText + value
    }

    fun putDot(currentText: String): String {

        if (!currentText.equals("") && !currentText.contains("."))
            return currentText + "."

        return currentText

    }

    fun makeOpposite(currentText: String): String {

        val firstLetter = currentText[0]

        if (currentText.equals("0"))
            return currentText

        if (firstLetter.equals('-'))
            return currentText.substring(1, currentText.length)

        return "-" + currentText
    }

    fun deleteDigit(currentText: String): String {

        return currentText.substring(0, currentText.length - 1)

    }

}