package files

import corporation.WhatToDo
import corporation.WhatToDo.*
import java.io.File

fun main() {
    var text: String
    val tasksList = File("Tasks.txt")
    val taskList2 = File("Tasks2.txt")
    while(true){
        val tasksOperation = OperationNumber.entries
        print("Choose operation number: ")
        for((index,action) in tasksOperation.withIndex()){
            print("$index - ${action.title}")
            if(index < tasksOperation.size-1){
                print(", ")
            }
            else{
                print(": ")
            }
        }
        val actionIndex = readln().toInt()
        val action: OperationNumber = tasksOperation[actionIndex]
        when (action) {
            OperationNumber.EXIT -> {
                println("Bye")
                break
            }
            OperationNumber.ADD_NEW_TASK -> {
                println("Enter your task: ")
                text = readln()
                appendToFile(text,taskList2)
            }
            OperationNumber.PRINT_LIST -> {
                readFile(taskList2)
            }
        }
    }

}

fun appendToFile(task: String, file : File) {
    file.appendText("$task\n")
}
fun readFile(file: File){
    val read = file.readText().trim()
    val items = read.split("\n")

    for ((index, item) in items.withIndex()){
        println("$index - $item")
    }
}