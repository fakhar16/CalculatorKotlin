package com.samsung.calculatorkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.samsung.calculatorkotlin.databinding.ActivityMainBinding
import com.samsung.calculatorkotlin.utils.Utils
import com.samsung.calculatorkotlin.utils.Utils.Companion.TAG
import java.util.Stack
import kotlin.math.acos
import kotlin.math.acosh
import kotlin.math.asin
import kotlin.math.asinh
import kotlin.math.atanh
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.sinh
import kotlin.math.sqrt
import kotlin.math.tan
import kotlin.math.tanh

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var isOperatorPressed : Boolean = false

    private var inputStack = Stack<String>()
    private var isSecond : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setButtonsListeners()
        setupUi()
        if (savedInstanceState != null)
            binding.resultTv.text = savedInstanceState.getString("input_value", "0")
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi() {
        binding.x2?.text = Utils.getSpannableStringBuilderForXPowerY("x2", "2")
        binding.x3?.text = Utils.getSpannableStringBuilderForXPowerY("x3", "3")
        binding.xy?.text = Utils.getSpannableStringBuilderForXPowerY("xy", "y")
        binding.ex?.text = Utils.getSpannableStringBuilderForXPowerY("ex", "x")
        binding.x10?.text = Utils.getSpannableStringBuilderForXPowerY("10x", "x")
        binding.second?.text = Utils.getSpannableStringBuilderForXPowerY("2nd", "nd")
        binding.xp2?.text = Utils.getSpannableStringBuilderForXPowerY("2x", "x")
        binding.log10?.text = Utils.getSpannableStringBuilderForXlogY("log10", "10")

        binding.squareroot2?.text = "2${getString(R.string.sqr_root)}x"
        binding.squareroot3?.text = "3${getString(R.string.sqr_root)}x"
        binding.ydx?.text = "y${getString(R.string.sqr_root)}x"

        binding.sin1?.text = Utils.getSpannableStringBuilderForXPowerY("sin-1", "-1")
        binding.cos1?.text = Utils.getSpannableStringBuilderForXPowerY("cos-1", "-1")
        binding.tan1?.text = Utils.getSpannableStringBuilderForXPowerY("tan-1", "-1")

        binding.sinh1?.text = Utils.getSpannableStringBuilderForXPowerY("sinh-1", "-1")
        binding.cosh1?.text = Utils.getSpannableStringBuilderForXPowerY("cosh-1", "-1")
        binding.tanh1?.text = Utils.getSpannableStringBuilderForXPowerY("tanh-1", "-1")

        binding.yx?.text = Utils.getSpannableStringBuilderForXPowerY("yx", "x")

        binding.log2?.text = Utils.getSpannableStringBuilderForXlogY("log2", "2")
        binding.logy?.text = Utils.getSpannableStringBuilderForXlogY("logy", "y")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("input_value", binding.resultTv.text.toString())
    }

    private fun setButtonsListeners() {
        binding.allClear.setOnClickListener(this)
        binding.plusMinus.setOnClickListener(this)
        binding.percent.setOnClickListener(this)
        binding.divide.setOnClickListener(this)
        binding.n7.setOnClickListener(this)
        binding.n8.setOnClickListener(this)
        binding.n9.setOnClickListener(this)
        binding.multiply.setOnClickListener(this)
        binding.n4.setOnClickListener(this)
        binding.n5.setOnClickListener(this)
        binding.n6.setOnClickListener(this)
        binding.minus.setOnClickListener(this)
        binding.n1.setOnClickListener(this)
        binding.n2.setOnClickListener(this)
        binding.n3.setOnClickListener(this)
        binding.plus.setOnClickListener(this)
        binding.n0.setOnClickListener(this)
        binding.dot.setOnClickListener(this)
        binding.equal.setOnClickListener(this)
    }


    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.second -> {
                isSecond = !isSecond

                if (isSecond) {
                    binding.second?.background = getDrawable(R.drawable.button_pressed)
                    binding.second?.setTextColor(getColor(R.color.black))
                    binding.sin?.visibility = View.GONE
                    binding.cos?.visibility = View.GONE
                    binding.tan?.visibility = View.GONE

                    binding.sinh?.visibility = View.GONE
                    binding.cosh?.visibility = View.GONE
                    binding.tanh?.visibility = View.GONE

                    binding.ex?.visibility = View.GONE
                    binding.ln?.visibility = View.GONE
                    binding.log10?.visibility = View.GONE
                    binding.x10?.visibility = View.GONE


                    binding.sin1?.visibility = View.VISIBLE
                    binding.cos1?.visibility = View.VISIBLE
                    binding.tan1?.visibility = View.VISIBLE

                    binding.sinh1?.visibility = View.VISIBLE
                    binding.cosh1?.visibility = View.VISIBLE
                    binding.tanh1?.visibility = View.VISIBLE

                    binding.yx?.visibility = View.VISIBLE
                    binding.logy?.visibility = View.VISIBLE
                    binding.log2?.visibility = View.VISIBLE
                    binding.xp2?.visibility = View.VISIBLE
                } else {
                    binding.second?.background = getDrawable(R.drawable.button_dark_black)
                    binding.second?.setTextColor(getColor(R.color.white))
                    binding.sin?.visibility = View.VISIBLE
                    binding.cos?.visibility = View.VISIBLE
                    binding.tan?.visibility = View.VISIBLE

                    binding.sinh?.visibility = View.VISIBLE
                    binding.cosh?.visibility = View.VISIBLE
                    binding.tanh?.visibility = View.VISIBLE

                    binding.ex?.visibility = View.VISIBLE
                    binding.ln?.visibility = View.VISIBLE
                    binding.log10?.visibility = View.VISIBLE
                    binding.x10?.visibility = View.VISIBLE

                    binding.sin1?.visibility = View.GONE
                    binding.cos1?.visibility = View.GONE
                    binding.tan1?.visibility = View.GONE

                    binding.sinh1?.visibility = View.GONE
                    binding.cosh1?.visibility = View.GONE
                    binding.tanh1?.visibility = View.GONE

                    binding.yx?.visibility = View.GONE
                    binding.logy?.visibility = View.GONE
                    binding.log2?.visibility = View.GONE
                    binding.xp2?.visibility = View.GONE
                }
            }
            R.id.x2 -> {
                val num = binding.resultTv.text.toString().toDouble()
                printResult(num.pow(2.0))
            }
            R.id.x3 -> {
                val num = binding.resultTv.text.toString().toDouble()
                printResult(num.pow(3.0))
            }
            R.id.equal -> {
                if (inputStack.empty())
                    return
                val num2 = binding.resultTv.text.toString()
                val operator = inputStack.pop()
                val num1 = inputStack.pop()
                val result = Utils.computeResult(num1.toDouble(), num2.toDouble(), operator)
                printResult(result)
            }
            R.id.percent -> {
                val value = binding.resultTv.text.toString().toDouble() / 100.0
                printResult(value)
                inputStack.clear()
            }
            R.id.plus_minus -> {
                val value = binding.resultTv.text.toString().toDouble() * -1
                printResult(value)
                inputStack.clear()
            }
            R.id.plus,
            R.id.minus,
            R.id.divide,
            R.id.multiply -> {
                computeResult(view)
            }
            R.id.xy -> {
                binding.xy?.background = getDrawable(R.drawable.button_pressed)
                binding.xy?.setTextColor(getColor(R.color.black))
                computeResult(view)
            }
            R.id.ex -> {
                val num = binding.resultTv.text.toString().toDouble()
                printResult(exp(num))
            }
            R.id.x10 -> {
                val num = binding.resultTv.text.toString().toDouble()
                printResult(10.0.pow(num))
            }
            R.id.half -> {
                val num = binding.resultTv.text.toString().toDouble()
                if (num == 0.0)
                    return
                printResult(1/num)
            }
            R.id.squareroot2 -> {
                printResult(sqrt(binding.resultTv.text.toString().toDouble()))
            }
            R.id.squareroot3 -> {
                val num = binding.resultTv.text.toString().toDouble()
                printResult(Math.cbrt(num))
            }
            R.id.ydx -> {
                binding.ydx?.background = getDrawable(R.drawable.button_pressed)
                binding.ydx?.setTextColor(getColor(R.color.black))
                computeResult(view)
            }
            R.id.ln -> {
                printResult(ln(binding.resultTv.text.toString().toDouble()))
            }
            R.id.log10 -> {
                printResult(log10(binding.resultTv.text.toString().toDouble()))
            }
            R.id.log2 -> {
                printResult(log2(binding.resultTv.text.toString().toDouble()))
            }
            R.id.factorial -> {
                printResult(Utils.factorial(binding.resultTv.text.toString().toInt()).toDouble())
            }
            R.id.sin -> {
                printResult(kotlin.math.sin(binding.resultTv.text.toString().toDouble()))
            }
            R.id.cos -> {
                printResult(cos(binding.resultTv.text.toString().toDouble()))
            }
            R.id.tan -> {
                printResult(tan(binding.resultTv.text.toString().toDouble()))
            }
            R.id.sinh -> {
                printResult(sinh(binding.resultTv.text.toString().toDouble()))
            }
            R.id.cosh -> {
                printResult(cosh(binding.resultTv.text.toString().toDouble()))
            }
            R.id.tanh -> {
                printResult(tanh(binding.resultTv.text.toString().toDouble()))
            }
            R.id.sin_1 -> {
                printResult(asin(binding.resultTv.text.toString().toDouble()))
            }
            R.id.cos_1 -> {
                printResult(acos(binding.resultTv.text.toString().toDouble()))
            }
            R.id.tan_1 -> {
                printResult(kotlin.math.atan(binding.resultTv.text.toString().toDouble()))
            }
            R.id.sinh_1 -> {
                printResult(asinh(binding.resultTv.text.toString().toDouble()))
            }
            R.id.cosh_1 -> {
                printResult(acosh(binding.resultTv.text.toString().toDouble()))
            }
            R.id.tanh_1 -> {
                printResult(atanh(binding.resultTv.text.toString().toDouble()))
            }
            R.id.e -> {
                printResult(Math.E)
            }
            R.id.pi -> {
                printResult(Math.PI)
            }
            R.id.all_clear -> {
                resetOperators()
                inputStack.clear()
                binding.resultTv.text = "0"
            }
            R.id.dot -> {
                if (binding.resultTv.text.length == 9)
                    return
                if (binding.resultTv.text.toString() == "0")
                    binding.resultTv.append("0.")
                else
                    binding.resultTv.append(".")

            }
            R.id.n1,
            R.id.n2,
            R.id.n3,
            R.id.n4,
            R.id.n5,
            R.id.n6,
            R.id.n7,
            R.id.n8,
            R.id.n9 -> {
                updateInput(view)
            }
            R.id.n0 -> {
                if (binding.resultTv.text.length == 9)
                    return
                if (binding.resultTv.text.toString() != "0")
                    binding.resultTv.append((view as Button).text)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun resetOperators() {
        binding.xy?.background = getDrawable(R.drawable.button_dark_black)
        binding.xy?.setTextColor(getColor(R.color.white))

        binding.ydx?.background = getDrawable(R.drawable.button_dark_black)
        binding.ydx?.setTextColor(getColor(R.color.white))
    }

    private fun computeResult(view: View?) {
        isOperatorPressed = true
        if (inputStack.empty()) {
            inputStack.push(binding.resultTv.text.toString())
            inputStack.push((view as Button).text.toString())
            Log.i(TAG, "computeResult: $inputStack")
        } else if (inputStack.size > 1) {
            val num2 = binding.resultTv.text.toString()
            val operator = inputStack.pop()
            val num1 = inputStack.pop()
            val result = Utils.computeResult(num1.toDouble(), num2.toDouble(), operator)
            printResult(result)
            inputStack.push(result.toString())
            inputStack.push((view as Button).text.toString())
        } else if (Utils.checkIfOperator(inputStack.peek())) {
            inputStack.pop()
            inputStack.push((view as Button).text.toString())
        }
    }

    private fun updateInput(view: View?) {
        resetOperators()
        if (binding.resultTv.text.length == 9)
            return

        if (binding.resultTv.text.toString() == "0" || isOperatorPressed)
            binding.resultTv.text = (view as Button).text
        else
            binding.resultTv.append((view as Button).text)

        isOperatorPressed = false
    }

    private fun printResult(result: Double) {
        if (result % 1.0 == 0.0) {
            binding.resultTv.text = buildString {
                append("")
                append(result.toLong())
            }
        } else {
            binding.resultTv.text = buildString {
                append("")
                append((result))
            }
        }
    }
}