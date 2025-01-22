package employee

import Products.Food
import Products.HouseholdAppliances
import Products.ProductRepository
import Products.Sneakers
import corporation.ProductType
import corporation.ProductType.*
import corporation.WhatToDo
import corporation.WhatToDo.*
import corporation.WorkersRepository


class Accountant(
    name: String,
    age: Int,
    id: Int,
    salary: Int
) : Worker(name = name, age = age,salary = salary, position = EmployeeType.ACCOUNTANT, id = id), Cleaner, Supplier {

    private val workersRepository = WorkersRepository()
    private val productRepository = ProductRepository()
    override fun work() {
        while (true) {
            val actions = WhatToDo.entries
            print("Choose action:\n")
            for((index,action) in actions.withIndex()){
                print("$index - ${action.title}")
                if(index < actions.size-1){
                    print(", \n")
                }
                else{
                    print(": \n")
                }
            }
            val actionIndex = readln().toInt()
            val action: WhatToDo = actions[actionIndex]
            when (action) {
                BREAK -> {
                    print("Bye!!!")
                    break
                }
                CREATE_NEW_ITEM -> {
                    registerNewItem()
                }

                SHOW_ALL_ITEMS -> {
                    showAllItems()
                }

                DELETE -> {
                    removeProductCard()
                }

                NEW_EMPLOYEE -> {
                    registerNewEmployee()
                }
                FIRE_AN_EMPLOYEE -> {
                    fireEmployee()
                }
                SHOW_ALL_EMPLOYEE -> {
                    showAllEmployee()
                }

                CHANGE_SALARY -> changeSalary()
            }
        }
    }
    private fun changeSalary(){
        print("Enter employee`s Id to change salary: ")
        val id = readln().toInt()
        print("Enter new salary: ")
        val salary = readln().toInt()
        workersRepository.changeSalary(id,salary)
    }

    private fun removeProductCard(){

        print("Enter name of card for removing:")
        val name = readln()
        productRepository.removeProductCard(name)
    }


    private fun showAllItems(){
        val cards = productRepository.loadAllCards()
        for(card in cards){
            card.printInfo()
        }
    }
    private fun registerNewItem() {
        val productTypes = ProductType.entries
        print("Enter the product type:")
        for((index, type) in productTypes.withIndex()){
            print("$index - ${type.title} ")
            if(index < productTypes.size-1){
                print(", ")
            }
            else{
                print(": ")
            }
        }
        val productTypeIndex = readln().toInt()
        val productType: ProductType = productTypes[productTypeIndex]
        print("Enter the product name:")
        val productName = readln()

        print("Enter the brand: ")
        val productBrand = readln()

        print("Enter the price: ")
        val productPrice = readln().toFloat()

        val card = when (productType) {
            FOOD -> {
                print("Enter storage Time:")
                val productStorageTime = readln().toInt()
                Food(productName,productBrand,productPrice,productStorageTime)

            }

            APPLIANCE -> {
                print("Enter power in watts:")
                val productPower = readln().toInt()
                HouseholdAppliances(productName,productBrand,productPrice,productPower)

            }

            SHOE -> {
                print("Enter product size:")
                val productSize = readln().toFloat()
                Sneakers(productName,productBrand,productPrice,productSize)
            }
        }
        productRepository.registerNewItem(card)
    }


    private fun registerNewEmployee(){
        val positions = EmployeeType.entries
        print("Choose position - ")
        for ((index,position) in positions.withIndex()){
            print("$index - ${position.title}")
            if(index < positions.size - 1){
                print(", ")
            }else{
                print(": ")
            }
        }
        val positionIndex = readln().toInt()
        val position = positions[positionIndex]
        print("Enter id: ")
        val id = readln().toInt()
        print("Enter name: ")
        val name = readln()
        print("Enter age: ")
        val age = readln().toInt()
        print("Enter salary: ")
        val salary = readln().toInt()
        val worker = when(position){
            EmployeeType.DIRECTOR -> Director(id = id,name = name,age = age, salary = salary)
            EmployeeType.ASSISTANT -> Assistant(id = id,name = name,age = age,salary = salary )
            EmployeeType.CONSULTANT -> Consultant(id = id,name = name,age = age, salary = salary)
            EmployeeType.ACCOUNTANT -> Accountant(id = id,name = name,age = age, salary = salary)
        }
        worker.setSalary(salary)
       workersRepository.registerNewEmployee(worker)
    }


    private fun showAllEmployee(){
        val employees = workersRepository.loadAllEmployee()
        for (employee in employees){
            employee.printInfo()
        }
    }
     private fun fireEmployee() {
      println("Enter employee`s id to fire:")
        val id  = readln().toInt()
        workersRepository.fireEmployee(id = id)
    }

    override fun clean() {
        println("I`m accountant, and I clean a rubbish")
    }

    override fun buyThings() {
        println("I`m accountant, and I`m buying some things")
    }

}

