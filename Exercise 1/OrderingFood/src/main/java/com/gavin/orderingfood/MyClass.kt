package com.gavin.orderingfood

import jdk.jfr.Description

fun main() {
    val ListMenu: ArrayList<Menu> = ArrayList<Menu>()
    val ListOrder: ArrayList<Order> = ArrayList<Order>()
    ListMenu.add(Menu("Nasi Goreng", "Nasi goreng dengan telur mata kaki", 15))
    ListMenu.add(Menu("Mie godog", "Indomie di godog", 5))
    ListMenu.add(Menu("Nasi Sosis Mas Rusdi", "Nasi dengan sosis bakar", 10))



    do {
        println("Home")
        println("1. Make order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        println("Select: ")
        val select = readLine()!!.toIntOrNull()

        if (select == 1) {
            println("Customer Name: ")
            val customerName = readLine()!!

            val orderItems = ArrayList<OrderItem>()
            var totalPrice = 0

            println("Menu List")

            var index = 0
            for (menu: Menu in ListMenu) {
                println()
                println("${index + 1}. ${menu.name}")
                println("Description: ${menu.desc}")
                println("Price: $${menu.price}")

                index++
            }

            do {
                println()
                println("Menu Number (0 if done ordering): ")
                val choose = readLine()?.toIntOrNull()

                if (choose == null) {
                    println("Invalid input. Please enter a number.")
                    continue
                }
                if (choose == 0) {
                    break
                }
                if (choose < 1 || choose > ListMenu.size) {
                    println("Invalid menu number.")
                    continue
                }

                println("Quantity: ")
                val quantity = readLine()?.toIntOrNull()

                if (quantity == null || quantity <= 0) {
                    println("Invalid quantity.")
                    continue
                }

                val temp = OrderItem(ListMenu[choose - 1], quantity)
                orderItems.add(temp)
                totalPrice += ListMenu[choose - 1].price * quantity
            } while (true)

            val order = Order(customerName, orderItems, totalPrice)

            ListOrder.add(order)

            println()
            println("Order successfully created!")
            println("Total Price: $$totalPrice")
        } else if (select == 2) {
            println("Order List")

            var index = 1

            for (order: Order in ListOrder) {
                println()
                println("${index}. ${order.customerName}")

                for (item: OrderItem in order.items) {
                    println("${item.menu.name} x${item.quantity} $${item.menu.price * item.quantity}")
                }

                println("TOTAL $${order.totalPrice}")

                index++
            }
        }  else if (select == 3) {
            println("Menu List")
            var index = 1
            for (menu: Menu in ListMenu) {
                println()
                println("${index}. ${menu.name}")
                println("Description: ${menu.desc}")
                println("Price: $${menu.price}")

                index++
            }
        } else if (select == 4) {
            println("Add new Menu!!")
            println("Name: ")
            val name = readLine()!!
            println("Description: ")
            val desc = readLine()!!
            println("Price: ")
            val price = readLine()?.toIntOrNull()

            if (price == null || price <= 0) {
                println("Invalid price.")
                continue
            }

            val temp = Menu(name, desc, price)
            ListMenu.add(temp)

            println("Menu successfully added!")
        } else if (select == 5) {
            println("Menu List")
            var index = 0
            for (menu: Menu in ListMenu) {
                println()
                println("${index + 1}. ${menu.name}")
                println("Description: ${menu.desc}")
                println("Price: $${menu.price}")

                index++
            }

            var choose: Int?
            do {
                println("Edit Number: ")
                choose = readLine()?.toIntOrNull()

                if (choose == null || choose < 1 || choose > ListMenu.size) {
                    println("Invalid menu number.")
                }
            } while (choose == null || choose < 1 || choose > ListMenu.size)

            println("Name: ")
            val name = readLine()!!
            println("Description: ")
            val desc = readLine()!!

            var price: Int?
            do {
                println("Price: ")
                price = readLine()?.toIntOrNull()

                if (price == null || price <= 0) {
                    println("Invalid price.")
                }
            } while (price == null || price <= 0)

            val temp = Menu(name, desc, price)
            ListMenu[choose - 1] = temp

            println("Menu successfully edited!")
        } else if (select == 6) {
            println("Menu List")
            var index = 0
            for (menu: Menu in ListMenu) {
                println()
                println("${index + 1}. ${menu.name}")
                println("Description: ${menu.desc}")
                println("Price: $${menu.price}")

                index++
            }

            var choose: Int?
            do {
                println("Delete Number: ")
                choose = readLine()?.toIntOrNull()

                if (choose == null || choose < 1 || choose > ListMenu.size) {
                    println("Invalid menu number.")
                }
            } while (choose == null || choose < 1 || choose > ListMenu.size)

            ListMenu.removeAt(choose - 1)

            println("Menu successfully deleted!")
        } else if (select == 7) {
            println("ThankYou!!")
        } else {
            println("Invalid input")
        }
    } while (select != 7)
}