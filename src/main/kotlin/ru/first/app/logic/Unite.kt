package ru.first.app.logic

class Unite {
    fun test(str: String):ReturnMap{
        val counting = Counting()
        // Каждый раз в начале присваеваем true
        StaticClass.isTrue = true
        // Проверяем строку на валидность если не валидна выводим ошибку
        Validation().exceptionTask(str)
        if (!StaticClass.isTrue)
            return ReturnMap(isTrue = false, isString = StaticClass.isString)
        // помещаем строку с заданием с стек
        val taskStack: Stack<String> = StringToArray().returnTask(str)
        // Производим вычисление и возвращаем ответ
        counting.returnAnswer(taskStack)

//        return ReturnMap(isTrue = StaticClass.isTrue, isString = StaticClass.isString)
        return ReturnMap(isTrue = StaticClass.isTrue, isString = StaticClass.isString)
    }
}