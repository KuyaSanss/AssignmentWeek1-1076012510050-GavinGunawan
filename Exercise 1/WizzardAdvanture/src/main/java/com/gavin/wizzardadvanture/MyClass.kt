package com.gavin.wizzardadvanture

fun main() {
    println("What's your name?")
    val name = readLine()

    if (name == null || name.isBlank()) {
        println("Invalid name.")
        return
    }

    var playAgain = true

    while (playAgain) {
        val wizard = Wizard(name)

        println()
        println("Good luck, ${wizard.name}! You're gonna need it!")

        var running = true

        while (running) {
            println()
            println("What're you going to do?")
            println("1. View Stats")
            println("2. Enter battle")
            println("Select: ")

            val select = readLine()?.toIntOrNull()

            if (select == 1) {
                showStats(wizard)
            } else if (select == 2) {
                val battle = Battle(wizard)
                val result = battle.start()

                if (!result) {
                    println()
                    println("${wizard.name} died.")
                    running = false
                }
            } else {
                println("Invalid input.")
            }
        }

        if (wizard.hp <= 0) {
            println()
            println("1. Restart")
            println("2. Exit")
            println("Select: ")

            val select = readLine()?.toIntOrNull()

            if (select == 1) {
                playAgain = true
            } else if (select == 2) {
                playAgain = false
                println("Goodbye!")
            } else {
                println("Invalid input.")
                playAgain = false
            }
        } else {
            playAgain = false
        }
    }
}

fun showStats(wizard: Wizard) {
    var running = true

    while (running) {
        println()
        println("---------- ${wizard.name}'s STATS ----------")
        println("HP: ${wizard.hp}/${wizard.maxHp}")
        println("Mana: ${wizard.mana}/${wizard.maxMana}")
        println("Kills needed to evolve: ${wizard.kills}/${wizard.killsNeeded}")
        println("Mana Potions held: ${wizard.manaPotions}")
        println("Health Potions held: ${wizard.healthPotions}")
        println("------------------------------------------")
        println("a. Drink Mana Potion")
        println("b. Drink Health Potion")
        println("c. Rename self")
        println("d. Back")
        println("Select: ")

        val select = readLine()?.lowercase()

        if (select == "a") {
            wizard.drinkManaPotion()
        } else if (select == "b") {
            wizard.drinkHealthPotion()
        } else if (select == "c") {
            println("New name: ")
            val newName = readLine()

            if (newName != null && newName.isNotBlank()) {
                wizard.name = newName
                println("Name changed!")
            } else {
                println("Invalid name.")
            }
        } else if (select == "d") {
            running = false
        } else {
            println("Invalid input.")
        }
    }
}