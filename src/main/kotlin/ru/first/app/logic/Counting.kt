package ru.first.app.logic

import java.text.DecimalFormat

/*
Класс производит математические действия над массивом с заданием
 */
class Counting {
    //
    private val regexSing: Regex = "^[/*+-]+$".toRegex()
    // Создаём стек под дробные цифры
    private val stkDouble: Stack<Double> = Stack()

    // Создаём стек под мат знаки
    private val stkSing: Stack<String> = Stack()

    // Создаём стек под задание
    private var stkTask: Stack<String> = Stack()

    // Размер стека с знаками
    private var sizeStkSing = 0

    // Переменная под значения с массива Очереди в который поместили разбитую строку
    private lateinit var whenString: String

    fun returnAnswer(stk: Stack<String>): String{
        stkTask = stk
        var str = ""
        try{
            calculator()
            str = aboutNumber(stkDouble.pop()).replace(",",".")

//            if(Regex("^([0-9-]+).0$").matches(str))
//                str = str.removeSuffix(".0")
            StaticClass.isString = str
        }
        catch (e: Exception){
            StaticClass.isTrue = false
            StaticClass.isString = e.toString().split(":")[1]
        }
        return str
    }

    private fun calculator(){
        // Переменная под временное хранение мат. знаков
        var tempSing: String
        // Переменная для запуска цикла чтения
        var whileBoolean = true

        while (whileBoolean) {
            whenString = stkTask.peek()
            if (whenString == "(") {
                remake()
            } else if (whenString == ")") {
                tempSing = stkSing.pop()
                if (tempSing == "(") {
                    sizeStkSing--
                    stkTask.pop()
                } else {
                    stkSing.push(tempSing)
                    lastAction()
                }
            } else if (regexSing.matches(whenString)) {
                if (sizeStkSing <= 0 || stkSing.peek() == "("
                    || singPriority(whenString, stkSing.peek()) == 1)
                    remake()
                else if (singPriority(whenString, stkSing.peek()) == 2)
                    lastAction()
                else
                    lastAction()
            } else {
                stkDouble.push(whenString.toDouble())
                stkTask.pop()
            }
            if (stkTask.isEmpty())
                whileBoolean = false

        }
        if (!stkSing.isEmpty()) {
            for (i in 1..sizeStkSing){
                lastAction()
            }
        }
    }
    // TODO Функцию нужно будет переписать, написать самостоятельное округление
    private fun aboutNumber(value: Double ):String{
        if (value<999999999999999.9)
            return DecimalFormat("0.##########").format(value)
        return value.toString()
    }

    //Функция определяет приоритет мат. знака над предыдущим в стеке
    private fun singPriority(value1: String, value2: String): Int {
        if (prioritySing(value1) > prioritySing(value2))
            return 1
        else if (prioritySing(value1) == prioritySing(value2))
            return 2
        return 3
    }

    // Функция возвращает приоритет мат. знака
    private fun prioritySing(value: String): Int = when (value) {
        ")" -> 0
        "(" -> 0
        "+" -> 1
        "-" -> 1
        "*" -> 2
        "/" -> 2
        else -> 0
    }

    //Функция выполняет математическое действие
    private fun mathematicalAction(num2: Double, num1: Double, sing: String): Double {
        return when (sing) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    throw Exception(Errors.BY_ZERO.str)
                }
                num1 / num2
            }
            else -> 0.0
        }
    }

    // Функция для математического действия последних двух чисел с стека и перезаписи
    // получившегося значения в стек. Так же уменьшаем значение переменной размер
    // стека знаков
    private fun lastAction() {
        sizeStkSing--
        stkDouble.push(mathematicalAction(stkDouble.pop(), stkDouble.pop(), stkSing.pop()))
    }

    // Функция переносит математичиский знак с стека с заданием с стек с знаками
    private fun remake() {
        sizeStkSing++
        stkSing.push(whenString)
        stkTask.pop()
    }
}