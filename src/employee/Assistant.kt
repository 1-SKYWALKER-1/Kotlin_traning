package employee

class Assistant(
    name: String,
    age: Int,
    id: Int,
    salary: Int
): Worker(name = name, age = age,salary = salary, position = EmployeeType.ASSISTANT, id = id),Cleaner, Supplier {
    override fun work(){
        //println("I`m, assistant and I`m give a coffee!")
    }


    fun bringCoffee(typeCoffee: String = "Cappuccino"): String{
        //println("Assistant: I`m brought your $typeCoffee\n")
        return "Espresso"
    }
    override fun clean() {
        println("I`m Assistant, and I clean a rubbish")
    }

    override fun buyThings() {
        println("I`m Assistant and I`m buying some Things")
    }
}