package Products

import corporation.ProductType
import corporation.ProductType.*
import java.io.File

class ProductRepository {
    private val fileProductCard = File("Products_cards.txt")

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
    fun registerNewItem(productCard: ProductCard){
        saveProductCardToFile(productCard)
    }



     fun loadAllCards(): MutableList<ProductCard>{
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
     fun removeProductCard(name:String){
        val cards: MutableList<ProductCard> = loadAllCards()
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
}