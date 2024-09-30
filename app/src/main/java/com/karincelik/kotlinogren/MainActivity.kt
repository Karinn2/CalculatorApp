package com.karincelik.kotlinogren

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentNumber = ""
    private var operator = ""
    private var firstNumber = 0.0
    private var secondNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TextView
        resultTextView = findViewById(R.id.resultTextView)

        // Sayı butonları
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        // Diğer Butonlar
        val buttonAC: Button = findViewById(R.id.buttonAC)        // AC (All Clear) butonu
        val buttonPlusMinus: Button = findViewById(R.id.buttonPlusMinus)  // +/- butonu
        val buttonPercent: Button = findViewById(R.id.buttonPercent)      // % butonu

        // Sayı butonları tıklanma
        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonDot.setOnClickListener { appendNumber(".") }

        // İşlem butonları
        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }

        // Eşittir butonuna tıklanma
        buttonEquals.setOnClickListener { calculateResult() }

        // diğer butonlar tıklanma
        buttonAC.setOnClickListener { clearAll() } // AC butonu
        buttonPlusMinus.setOnClickListener { toggleSign() } // +/- butonu
        buttonPercent.setOnClickListener { calculatePercentage() } // % butonu
    }

    // Sayıları TextView'e ekleme
    private fun appendNumber(number: String) {
        currentNumber += number
        resultTextView.text = currentNumber
    }

    // Operatörü belirleme
    private fun setOperator(selectedOperator: String) {
        operator = selectedOperator
        firstNumber = currentNumber.toDoubleOrNull() ?: 0.0
        currentNumber = "" //İlk sayı belirlendikten sonra, ikinci sayıyı girebilmek için currentNumber sıfırlanıyor.
    }

    // Sonuç hesaplama
    private fun calculateResult() {
        secondNumber = currentNumber.toDoubleOrNull() ?: 0.0
        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Hata"
            else -> "Hata"
        }
        resultTextView.text = result.toString() //Sonuç ekranda gösterilir
        currentNumber = result.toString()
    }

    // AC (All Clear) butonu fonksiyonu
    private fun clearAll() {
        currentNumber = ""
        firstNumber = 0.0
        secondNumber = 0.0
        operator = ""
        resultTextView.text = "0"
    }

    // +/- butonu fonksiyonu
    private fun toggleSign() { //Sayının işaretini (+/-) değiştirir.
        if (currentNumber.isNotEmpty()) {
            if (currentNumber.startsWith("-")) {
                currentNumber = currentNumber.substring(1)
            } else {
                currentNumber = "-$currentNumber"
            }
            resultTextView.text = currentNumber
        }
    }

    // % butonu fonksiyonu
    private fun calculatePercentage() {//Girilen sayıyı yüzdeye çevirir
        if (currentNumber.isNotEmpty()) {
            val number = currentNumber.toDoubleOrNull() ?: 0.0
            currentNumber = (number / 100).toString()
            resultTextView.text = currentNumber
        }
    }
}
