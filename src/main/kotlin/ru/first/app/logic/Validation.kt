package ru.first.app.logic
/*
Класс для проверки строки на валидность.
exceptionTask() - в эту функцию передаётся строка с примером и она ёе проверяет.
Если строка не валидная то записываем в статическую переменную isTrue значени false
 и выбрасываем Exception для дальнейшей обработке в классе "сборщике".
 */
class Validation {
    // Объявляем переменную типо String для дальнейшего её использования во всём классе
    private var task: String = ""
    // Функция просматривает были ли ошибки
    fun exceptionTask(str: String){
        this.task = str
        try{
            searchError()
            StaticClass.isString = task
        }
        catch (e: Exception){
            StaticClass.isTrue = false
            StaticClass.isString = e.toString().split(":")[1]
        }
    }
    /*
    TODO Добавить проверку на количество введёных знаков в задание и после запятой в Double
      также проверять количетво знаков в начале примера и после открывающейся скобки
      и перед закрывающейся
     */
    // Функция проверяет на ошибки ввода в примере
    private fun searchError(){
        // Ниже регулярные выражения для проверки
        // Проверка на соответствие только указаным символам "0-9()+-*/."
        val regexNoValid = "[0-9()+\\-*/.]+".toRegex()
        // Проверка на соответствие только указаным символам "+-*/" не должен быть последним
        val regexLast = ".*[+\\-*/]$".toRegex()
        if (task.isEmpty()){
            throw Exception(Errors.EMPTY.str)
        }
        else if(!regexNoValid.matches(task))
            throw Exception(Errors.NO_VALID.str)
        else if(task.count { it == '(' } != task.count { it == ')' })
            throw Exception(Errors.BRACKET.str)
        else if(regexLast.matches(task))
            throw Exception(Errors.SING.str)
    }
}