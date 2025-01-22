package employee

class Consultant(
    name: String,
    age: Int = 0,
    id: Int,
    salary: Int
): Worker(name = name, age = age, position = EmployeeType.CONSULTANT, id = id, salary = salary), Cleaner {
    override fun work(){
        //println("I`m, consultant and I`m consulting people!")
    }

    override fun clean() {
        println("I`m consultant, and I clean a rubbish")
    }
}