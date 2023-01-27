package ru.first.app

import ru.first.app.logic.Unite

fun main() {
    var str = ""
    var test: String
    println("Для завершения работы приложения введите \"exit\"")
    while(str!="exit") {
        println("Введите математический пример:")
        str = readlnOrNull().toString()
        test =Unite().test(str).isString
        println(test)
    }
}
