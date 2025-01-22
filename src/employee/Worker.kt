package employee

abstract class Worker(
    val name: String,
    val age: Int = 0,
    val position: EmployeeType,
    val id: Int = 0,
    private var salary: Int = 15000
    ){


    fun setSalary(salary: Int){
        if (salary < this.salary){
            println("The new salary is to small...")
        }else{
            this.salary = salary
        }

    }
   fun getSalary() = this.salary

    open fun work (){
        println("Working...")
    }
     fun printInfo(){
        println(this)
    }

    override fun toString(): String {
        return "Id: $id, Name: $name, Age: $age, Position: $position, Salary: $salary"
    }
}
