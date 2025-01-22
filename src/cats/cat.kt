package cats

class Cat(val name: String) : CatsFamily() {
    fun playWithMouse(){
        println("I`m play with mouse")
    }

    override fun eat() {
        println("Cat eat Wiskas!")
    }
}