package Products

import corporation.ProductType

abstract class ProductCard(
    val name : String,
    val brand: String,
    val price : Float,
    val productType: ProductType
) {

    abstract fun printInfo()
}