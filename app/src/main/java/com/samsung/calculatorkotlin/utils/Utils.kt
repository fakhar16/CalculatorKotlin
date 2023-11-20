package com.samsung.calculatorkotlin.utils

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import kotlin.math.pow


class Utils {
    companion object {
        const val TAG = "console"

        fun computeResult(p1: Double, p2: Double, operator: String): Double {
            when (operator) {
                "+" -> {
                    return p1 + p2
                }

                "-" -> {
                    return p1 - p2
                }

                "/" -> {
                    return p1 / p2
                }

                "x" -> {
                    return p1 * p2
                }

                "xy" -> {
                    return p1.pow(p2)
                }

                "y√¯x" -> {
                    return p1.pow(1/p2)
                }
            }
            return 0.0
        }

        fun checkIfOperator(operator: String): Boolean {
            if (operator == "+" || operator == "-" || operator == "*" || operator == "/" || operator == "xy") {
                return true
            }
            return false
        }

        fun getSpannableStringBuilderForXPowerY (xy: String, y:String): SpannableStringBuilder {
            val superscriptSpan = SuperscriptSpan()
            val builder = SpannableStringBuilder(xy)
            builder.setSpan(
                superscriptSpan,
                xy.indexOf(y),
                xy.indexOf(y) + y.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            builder.setSpan(RelativeSizeSpan(0.5f), xy.indexOf(y), xy.indexOf(y) + y.length, 0)

            return builder
        }

        fun getSpannableStringBuilderForXlogY (xy: String, y:String): SpannableStringBuilder {
            val superscriptSpan = SubscriptSpan()
            val builder = SpannableStringBuilder(xy)
            builder.setSpan(
                superscriptSpan,
                xy.indexOf(y),
                xy.indexOf(y) + y.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            builder.setSpan(RelativeSizeSpan(0.5f), xy.indexOf(y), xy.indexOf(y) + y.length, 0)

            return builder
        }

        fun factorial(num: Int): Long {
            var result = 1L
            for (i in 2..num) result *= i
            return result
        }
    }
}