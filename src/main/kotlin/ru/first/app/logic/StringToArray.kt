package ru.first.app.logic
/*
Класс помещает строку с математическим примером в стек в реверсивном порядке, в дальнейшем
 будет удобней работать.
rework()                - добавляет знак "*" между скобками ")(" если был опущен.
createList()            - разбиваем строку регулярным выражением на цирфы и знаки, помещаем их
                          в изменяемый List.
createStack()           - Помещает List  с заданием в Stack и производит переворот над ним.
 */
class StringToArray {
    // Создаём объект класса Stack
    private val stackTask: Stack<String> = Stack()
    // Переменная под пример для использования во всём классе
    private lateinit var task: String

    fun returnTask(str: String): Stack<String>{
        task = str
        createStack()
        return stackTask
    }

    private fun createStack(){
        // Объявляем Флаг, будет служить для определения закончилось ли число или нет.
        // При значение в истена значение всегда добавляется в новую ячейку
        var intBoolean = true

        task = task.replace(")(",")*(")
        for(valueChar in task){
            // Проверяем является ли символ цифрой или точкой
            if(valueChar in '0'..'9' || valueChar == '.'){
                // Если цифра встретилась впервые то помещаем ёе в новую ячейку
                // и устанавливаем значение Флага в ложь, если дальше попадётся цифра
                // просто будем дописывать в этуже ячейку
                if(intBoolean) {
                    stackTask.push(valueChar.toString())
                    intBoolean = false
                }
                else
                    stackTask.push(stackTask.pop() + valueChar.toString())
            }
            // Любой знак записываем в новую ячейку и будем устанавливать Флаг в истину
            // ( возможно перед знаком флаг был ложь, дозаписывая цифры в ячейку
            else if (valueChar.toString().matches("^[()/*+^-]+$".toRegex())) {
                stackTask.push(valueChar.toString())
                intBoolean = true
            }
        }
        // Зеркалим массив с заданием(зделано для удобства и упрощения работы в стеке)
        stackTask.reverse()
    }
}