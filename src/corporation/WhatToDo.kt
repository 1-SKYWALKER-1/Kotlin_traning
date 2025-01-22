package corporation

enum class WhatToDo(val title : String) {
    BREAK(title = "break"),
    CREATE_NEW_ITEM(title = "Create new item"),
    SHOW_ALL_ITEMS("Show all items"),
    DELETE("Remove product card"),
    NEW_EMPLOYEE("Hire new employee"),
    FIRE_AN_EMPLOYEE("Fire an employee"),
    SHOW_ALL_EMPLOYEE("Show all employees"),
    CHANGE_SALARY("Change salary")

}