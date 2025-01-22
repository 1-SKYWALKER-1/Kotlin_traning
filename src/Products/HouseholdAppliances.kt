package Products

import corporation.ProductType

class HouseholdAppliances(
    name: String,
    brand: String,
    price: Float,
    val power: Int
) : ProductCard(name = name, brand = brand, price = price, productType = ProductType.APPLIANCE){

    override fun printInfo() {

        println("Name: $name Brand: $brand price: $price Power:${power}W Product Type: ${productType.title}")
    }
}