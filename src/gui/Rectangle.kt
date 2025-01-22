package gui

class Rectangle(
    var width: Int = 0,
    var height: Int = 0
) {

    val area: Int
        get() = width*height

}