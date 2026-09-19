package com.gavin.wizzardadvanture

class Battle(
    private val wizard: Wizard
) {
    val enemyTypes: ArrayList<String> = ArrayList<String>()

    init {
        enemyTypes.add("Fire")
        enemyTypes.add("Water")
        enemyTypes.add("Grass")
    }

    fun start(): Boolean {
        val randomType = enemyTypes.random()
        val enemy = Enemy(randomType)

        println()
        println("---------- BATTLE ----------")
        println(wizard.name)
        println("HP: ${wizard.hp}/${wizard.maxHp}")
        println("Mana: ${wizard.mana}/${wizard.maxMana}")
        println("HP Potions: ${wizard.healthPotions}")
        println("MP Potions: ${wizard.manaPotions}")
        println()
        println(enemy.getName())
        println("HP: ${enemy.hp}/${enemy.maxHp}")
        println("Type: ${enemy.type}")
        println("----------------------------")

        while (wizard.hp > 0 && enemy.hp > 0) {
            println()
            println("a. Fire Attack")
            println("b. Water Attack")
            println("c. Grass Attack")
            println("d. Drink potion")
            println("e. Run")
            println("Select: ")

            val select = readLine()?.lowercase()

            if (select == "a" || select == "b" || select == "c") {
                if (wizard.mana < 10) {
                    println("Not enough mana.")
                    continue
                }

                val attackType = when (select) {
                    "a" -> "Fire"
                    "b" -> "Water"
                    else -> "Grass"
                }

                wizard.mana -= 10

                val damage = wizard.attackDamage(enemy.type, attackType)
                enemy.hp -= damage

                println()
                println("${wizard.name} used $attackType Attack!")
                println("Damage: $damage")
                println("Enemy HP: ${enemy.hp}/${enemy.maxHp}")

                if (wizard.isStrong) {
                    wizard.hp = minOf(
                        wizard.hp + wizard.lifesteal,
                        wizard.maxHp
                    )

                    println("Lifesteal restored ${wizard.lifesteal} HP.")
                }

                if (enemy.hp <= 0) {
                    println()
                    println("Enemy defeated!")

                    wizard.addKill()

                    return true
                }

                wizard.hp -= 10

                println()
                println("Enemy dealt 10 damage!")
                println("Your HP: ${wizard.hp}/${wizard.maxHp}")

                if (wizard.hp <= 0) {
                    println()
                    println("You died!")
                    return false
                }
            } else if (select == "d") {
                println()
                println("1. Health Potion")
                println("2. Mana Potion")
                println("Select: ")

                val potion = readLine()?.toIntOrNull()

                if (potion == 1) {
                    wizard.drinkHealthPotion()
                } else if (potion == 2) {
                    wizard.drinkManaPotion()
                } else {
                    println("Invalid potion choice.")
                }
            } else if (select == "e") {
                println("You ran away!")
                return true
            } else {
                println("Invalid input.")
            }
        }

        return wizard.hp > 0
    }
}