package com.example.random_number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.abs


class MainActivity : AppCompatActivity() { // тут идёт наследование

    private fun randomNumber(left: Int, right: Int): Int {
        var someNumber = abs(java.util.Calendar.getInstance().timeInMillis.toInt())

        val first = someNumber shl 13
        val second = someNumber shr 10
        val third = someNumber shl 13
        someNumber = abs(someNumber or first or second or third)

        val diffRightandLeft = (right - left) + 1
        val generalNumber = someNumber % diffRightandLeft + left

        return generalNumber
    }

    var res: TextView? = null
    var button: Button? = null
    var from: EditText? = null
    var to: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        res = findViewById(R.id.result)
        button = findViewById(R.id.button)

        button?.setOnClickListener {

            from = findViewById(R.id.from)
            to = findViewById(R.id.to)

            val left = from?.text.toString().trim().toIntOrNull()
            val right = to?.text.toString().trim().toIntOrNull()

            if (left == null || right == null) {
                Toast.makeText(this, "Введите границы", Toast.LENGTH_SHORT).show()
            } else {
                if (left > right) {
                    Toast.makeText(this, "Границы введены неверно", Toast.LENGTH_SHORT).show()
                } else {

                    res?.text = randomNumber(left, right).toString()
                }
            }
        }
    }
}