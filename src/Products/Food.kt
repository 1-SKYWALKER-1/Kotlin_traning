package Products

import corporation.ProductType

class Food(
    name: String,
    brand: String,
    price: Float,
    val storageTime: Int
) : ProductCard(name = name, brand = brand, price = price, productType = ProductType.FOOD){


    override fun printInfo() {
        println("Name: $name Brand: $brand price: $price Storage time:$storageTime Product Type: ${productType.title}")
    }
}