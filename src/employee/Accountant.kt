package employee

import corporation.ProductType
import corporation.WhatToDo
import Products.Food
import Products.HouseholdAppliances
import Products.ProductCard
import Products.Sneakers
import corporation.ProductType.*
import corporation.WhatToDo.*
import corporation.WorkersRepository
import java.io.File


class Accountant(
    name: String,
    age: Int,
    id: Int,
    salary: Int
) : Worker(name = name, age = age,salary = salary, position = EmployeeType.ACCOUNTANT, id = id), Cleaner, Supplier {

    private val workersRepository = WorkersRepository()
    private val fileProductCard = File("Products_cards.txt")

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
        val employees = loadAllEmployee()
        for (employee in employees){
            fileWorkers.writeText("")
            if(employee.id == id){
                employee.setSalary(salary)
            }
            saveWorkerToFile(employee)
        }
    }

    private fun removeProductCard(){
        val cards: MutableList<ProductCard> = loadAllCards()
        print("Enter name of card for removing:")
        val name = readln()
        for(card in cards){
            if(card.name == name){
                cards.remove(card)
                break
            }
        }
        fileProductCard.writeText("")
        for(card in cards){
            saveProductCardToFile(card)
        }
    }

    private fun saveProductCardToFile(productCard: ProductCard){
        fileProductCard.appendText("${productCard.name}%${productCard.brand}%${productCard.price}%")
        when (productCard) {
            is Food -> {
                val productStorageTime = productCard.storageTime
                fileProductCard.appendText("$productStorageTime%")
            }

            is Sneakers -> {
                val productSize = productCard.size
                fileProductCard.appendText("$productSize%")
            }

            is HouseholdAppliances -> {
                val productPower = productCard.power
                fileProductCard.appendText("$productPower%")
            }
        }
        fileProductCard.appendText("${productCard.productType}\n")
    }

    private fun loadAllCards(): MutableList<ProductCard>{
        val cards = mutableListOf<ProductCard>()
        if(!fileProductCard.exists()) fileProductCard.createNewFile()
        val content =  fileProductCard.readText().trim()

        if(content.isEmpty()){
            return cards
        }

        val cardsAsString = content.split("\n")
        for(cardAsString in cardsAsString){
            val properties = cardAsString.split("%")
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toFloat()
            val type = properties.last()
            val productType = ProductType.valueOf(type)
            val productCard = when(productType){
                FOOD -> {
                    val productStorageTime = properties[3].toInt()
                    Food(name,brand,price,productStorageTime)
                }
                APPLIANCE -> {
                    val power  = properties[3].toInt()
                    HouseholdAppliances(name,brand,price,power)
                }
                SHOE -> {
                    val size = properties[3].toFloat()
                    Sneakers(name,brand,price,size)
                }
            }
            cards.add(productCard)
        }
        return cards
    }

    private fun showAllItems(){
        val cards = loadAllCards()
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
        saveProductCardToFile(card)
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
        saveWorkerToFile(worker)
    }



    private fun showAllEmployee(){
        val employees = loadAllEmployee()
        for (employee in employees){
            employee.printInfo()
        }
    }





    override fun clean() {
        println("I`m accountant, and I clean a rubbish")
    }

    override fun buyThings() {
        println("I`m accountant, and I`m buying some things")
    }

}

