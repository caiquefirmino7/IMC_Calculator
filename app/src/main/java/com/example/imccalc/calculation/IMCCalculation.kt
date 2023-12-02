package com.example.imccalc.calculation

import java.text.DecimalFormat

class IMCCalculation {

    var IMCResult = ""

    fun IMCCalculate(weight: String, height:String) {

        val sanitizedWeight = weight.replace(',', '.')
        val sanitizedHeight = height.replace(',', '.')
        try {
            val convertedWeight = sanitizedWeight.toDouble()
            val convertedHeight = sanitizedHeight.toDouble()


            val IMC = convertedWeight / (convertedHeight * convertedHeight)
            val decimalFormat = DecimalFormat("0.00")

            when {
                IMC < 17 -> IMCResult = "Muito abaixo do peso \n IMC: ${decimalFormat.format(IMC)}"
                IMC >= 17 && IMC < 18.5 -> IMCResult = "Abaixo do peso \n" +
                        " IMC: ${decimalFormat.format(IMC)}"

                IMC >= 18.5 && IMC < 25 -> IMCResult = "Peso normal \n" +
                        " IMC: ${decimalFormat.format(IMC)}"

                IMC >= 25 && IMC < 30 -> IMCResult = "Acima do peso \n" +
                        " IMC: ${decimalFormat.format(IMC)}"

                IMC >= 30 && IMC < 35 -> IMCResult = "Obesidade I \n" +
                        " IMC: ${decimalFormat.format(IMC)}"

                IMC >= 35 && IMC < 40 -> IMCResult = "Obesidade II (severa) \n" +
                        " IMC: ${decimalFormat.format(IMC)}"

                else -> IMCResult = "Obesidade III (mórbida) \n" +
                        " IMC: ${decimalFormat.format(IMC)}"
            }
        } catch (e: NumberFormatException) {
            IMCResult = "Formato inválido. Certifique-se de usar números válidos."

        }
        fun IMCResult(): String {
            return IMCResult
        }

    }

}