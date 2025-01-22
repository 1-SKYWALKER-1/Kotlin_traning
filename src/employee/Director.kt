package employee

class Director (
    name: String,
    age: Int,
    id: Int,
    salary: Int
): Worker(name = name, age = age,EmployeeType.DIRECTOR, id = id,salary = salary), Supplier {
    override fun work(){
        //println("I`m, a boss and I`m drink coffee!")
    }

    fun takeCoffee(assistant: Assistant){
        val drinkName : String = assistant.bringCoffee()
        //println("Thank you so much for a $drinkName ${assistant.name}!")
    }
    override fun buyThings() {
        println("I`m $position, and I`m buying some things")
    }
}