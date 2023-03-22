package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Numbers
        binding.btnOne.setOnClickListener { appendExpression("1",true) }
        binding.btnTwo.setOnClickListener { appendExpression("2",true) }
        binding.btnThree.setOnClickListener { appendExpression("3",true) }
        binding.btnFour.setOnClickListener { appendExpression("4",true) }
        binding.btnFive.setOnClickListener { appendExpression("5",true) }
        binding.btnSix.setOnClickListener { appendExpression("6",true) }
        binding.btnSeven.setOnClickListener { appendExpression("7",true) }
        binding.btnEight.setOnClickListener { appendExpression("8",true) }
        binding.btnNine.setOnClickListener { appendExpression("9",true) }
        binding.btnZero.setOnClickListener { appendExpression("0",true) }
        binding.btnDot.setOnClickListener { appendExpression(",",true) }



        // Operators
        binding.btnDivide.setOnClickListener {
            appendExpression("/",true)
        }
        binding.btnPercentage.setOnClickListener { appendExpression("%",true) }
        binding.btnMult.setOnClickListener { appendExpression("*",true) }
        binding.btnMinus.setOnClickListener { appendExpression("-",true) }
        binding.btnAdd.setOnClickListener { appendExpression("+",true) }
        binding.btnAC.setOnClickListener { deleteAll() }
        binding.btnEqual.setOnClickListener {
            calculate()
        }
        binding.btnDEL.setOnClickListener {
           deleteLast()
        }
    }

    private fun appendExpression(expression : String, clear : Boolean) {
        if(clear) {
            binding.tvExpression.append(expression)
        }else{
            binding.tvExpression.append(binding.tvResult.text)
            binding.tvExpression.append(expression)
            binding.tvResult.text = ""
        }

        try {
            val text = binding.tvExpression.text.toString()
            val expressionBuilder = ExpressionBuilder(text).build()

            val result = expressionBuilder.evaluate()
            val longResult = result.toLong()
            if(result == longResult.toDouble()) {
                binding.tvResult.text = longResult.toString()
            }else{
                binding.tvResult.text = result.toString()
            }

        } catch (e: IllegalArgumentException) {
            binding.tvResult.text = ""
        }
        // Division by zero exception
        catch (e: ArithmeticException) {
            binding.tvResult.text = getString(R.string.division_by_zero)
        }
    }
    private fun deleteAll() {
        binding.tvExpression.text = ""
        binding.tvResult.text = ""
    }

    private fun calculate() {
        val text = binding.tvExpression.text.toString()
        try {
            val expression = ExpressionBuilder(text.replace("%", "/100")).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if(result == longResult.toDouble()) {
                binding.tvResult.text = longResult.toString()
            }else{
                binding.tvResult.text = result.toString()
            }
        }catch (e : java.lang.Exception) {
            binding.tvResult.text = getString(R.string.exception)
        }
        binding.btnPercentage.setOnClickListener {
            appendExpression("/100", true)
        }
    }
    private fun deleteLast() {
        val string = binding.tvExpression.text.toString()
        if(string.isNotEmpty()) {
            binding.tvExpression.text = string.substring(0,string.length - 1)
        }
        binding.tvResult.text = ""
    }
    }



