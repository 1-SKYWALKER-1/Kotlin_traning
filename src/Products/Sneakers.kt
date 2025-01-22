package Products

import corporation.ProductType

class Sneakers(
    name: String,
    brand: String,
    price: Float,
    val size: Float
) : ProductCard(name = name,brand, price, productType = ProductType.SHOE){
    override fun printInfo() {

        println("Name: $name Brand: $brand price: $price Product Type: Size:$size ${productType.title}")
    }
}