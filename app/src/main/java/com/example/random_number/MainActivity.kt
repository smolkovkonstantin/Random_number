package com.example.random_number

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.abs


class MainActivity : AppCompatActivity() { // тут идёт наследование

    private fun randomNumber(left: Int, right: Int): Int {
        var someNumber = abs(java.util.Calendar.getInstance().timeInMillis.toInt())

        val first = someNumber shl (someNumber % 34 + 1)
        val second = someNumber shr (someNumber % 17 + 1)
        val third = someNumber shl (someNumber % 30 + 1)

        someNumber = abs(someNumber or first or second or third)

        var generalNumber: Int
        val rightSomeNumber = right - someNumber % right
        val leftSomeNumber = left - someNumber % left
        if (rightSomeNumber > left) {
            generalNumber = rightSomeNumber
        } else {
            val diffRightandLeft = abs(rightSomeNumber - leftSomeNumber)
            generalNumber = left + diffRightandLeft
            if (generalNumber > right) {
                generalNumber = right
            }
        }
        return generalNumber
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val res: TextView = findViewById(R.id.result)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {

            val from: EditText? = findViewById(R.id.from)
            val to: EditText? = findViewById(R.id.to)


            val left = from?.text.toString().trim().toIntOrNull()
            val right = to?.text.toString().trim().toIntOrNull()

            if (left == null || right == null) {
                Toast.makeText(this, "Введите границы", Toast.LENGTH_SHORT).show()
            } else {
                if (left > right) {
                    Toast.makeText(this, "Границы введены неверно", Toast.LENGTH_SHORT).show()
                } else {

                    res.text = randomNumber(left, right).toString()
                }
            }
        }
    }
}