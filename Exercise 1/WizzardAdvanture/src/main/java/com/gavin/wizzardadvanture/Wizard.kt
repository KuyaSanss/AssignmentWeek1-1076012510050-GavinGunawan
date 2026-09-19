package com.gavin.wizzardadvanture

class Wizard(
    var name: String
) {
    var maxHp = 50
    var hp = 50
    var maxMana = 30
    var mana = 30
    var kills = 0
    var killsNeeded = 5
    var manaPotions = 5
    var healthPotions = 5
    var lifesteal = 0
    var isStrong = false

    fun drinkManaPotion() {
        if (manaPotions <= 0) {
            println("You don't have any mana potions.")
            return
        }

        mana = minOf(mana + 15, maxMana)
        manaPotions--
        println("Mana restored!")
    }

    fun drinkHealthPotion() {
        if (healthPotions <= 0) {
            println("You don't have any health potions.")
            return
        }

        hp = minOf(hp + 25, maxHp)
        healthPotions--
        println("Health restored!")
    }

    fun addKill() {
        kills++

        if (!isStrong && kills >= killsNeeded) {
            evolve()
        } else if (isStrong) {
            lifesteal++
        }
    }

    fun evolve() {
        isStrong = true
        maxHp = 75
        hp = 75
        maxMana = 45
        mana = 45
        lifesteal = 1

        println()
        println("Congratulations!")
        println("$name has evolved into a strong wizard!")
        println("HP increased to $maxHp")
        println("Mana increased to $maxMana")
        println("Lifesteal gained: $lifesteal")
    }

    fun attackDamage(enemyType: String, attackType: String): Int {
        var damage = 10

        if (
            attackType == "Fire" && enemyType == "Grass" ||
            attackType == "Water" && enemyType == "Fire" ||
            attackType == "Grass" && enemyType == "Water"
        ) {
            damage *= 2
        }

        if (isStrong) {
            damage = (damage * 1.5).toInt()
        }

        return damage
    }
}