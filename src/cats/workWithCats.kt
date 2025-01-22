package cats

fun main() {
    val cat = Cat(name ="Jorik")
    println(cat.legsCount)
    println(cat.name)
    val lion = Lion(personInPride = 25)
    println(lion.personInPride)
    println(lion.legsCount)
    cat.playWithMouse()
    lion.eat()
    cat.eat()
}