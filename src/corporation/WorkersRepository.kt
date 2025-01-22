package corporation

import employee.*
import java.io.File

class WorkersRepository {

    private val fileWorkers = File("Workers.txt")

     private fun saveWorkerToFile(worker: Worker){
        fileWorkers.appendText("${worker.id}%${worker.name}%${worker.age}%${worker.getSalary()}%${worker.position}\n")
    }
    fun registerNewEmployee(worker: Worker){
        saveWorkerToFile(worker)
    }
    fun loadAllEmployee(): MutableList<Worker>{
        val employees = mutableListOf<Worker>()
        if(!fileWorkers.exists()) fileWorkers.createNewFile()

        val content = fileWorkers.readText().trim()

        if(content.isEmpty()) return employees

        val employeesAsText = content.split("\n")
        for(employeeAsText in employeesAsText){
            val properties = employeeAsText.split("%")
            val id = properties[0].toInt()
            val name = properties[1]
            val age = properties[2].toInt()
            val salary = properties[3].toInt()
            val positionAsText = properties.last()
            val position = EmployeeType.valueOf(positionAsText)
            val worker = when(position){
                EmployeeType.DIRECTOR -> Director(id = id,name = name,age = age, salary = salary)
                EmployeeType.ASSISTANT -> Assistant(id = id,name = name,age = age, salary = salary)
                EmployeeType.CONSULTANT -> Consultant(id = id,name = name,age = age, salary = salary)
                EmployeeType.ACCOUNTANT -> Accountant(id = id,name = name,age = age, salary = salary)
            }
            worker.setSalary(salary)
            employees.add(worker)
        }
        return employees
    }

     fun fireEmployee(id: Int) {
        val employees = loadAllEmployee()
        for (employee in employees){
            fileWorkers.writeText("")
            if(employee.id != id){
                saveWorkerToFile(employee)
            }
        }
    }
     fun changeSalary(id: Int, salary: Int){

        val employees = loadAllEmployee()
        for (employee in employees){
            fileWorkers.writeText("")
            if(employee.id == id){
                employee.setSalary(salary)
            }
            saveWorkerToFile(employee)
        }
    }
}
