package ru.first.app.logic
/*
Класс под стек на базе изменяемого List для любого типа данных
push()          - Добавляет значение в конец стека
pop()           - Возвращаем последнее значение в стеке и уменьшаем его на 1
peek()          - Возвращает последнее значение в стеке, не уменьшая его
isEmpty()       - Проверяем пуст ли стек, если пуст возвращаем true
reverse()       - Перезаписываем стек в обратном подрядке
 */
class Stack<T> {
    // Объявляем и инициализируем изменяемую коллекцию List под стек
    private val stack: MutableList<T> = mutableListOf()
    fun push(value: T){
        stack.add(value)
    }
    fun pop(): T = stack.removeLast()
    fun peek(): T =  stack.last()
    fun isEmpty(): Boolean = stack.isEmpty()
    fun reverse() {
        stack.reverse()
    }
}