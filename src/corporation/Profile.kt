package corporation

import employee.*

fun main(){
    val accountant = Accountant(name = "Klara", age = 274, id = 1000, salary = 60000)
    val employees: MutableList<Worker> = accountant.loadAllEmployee()
    accountant.work()
}

