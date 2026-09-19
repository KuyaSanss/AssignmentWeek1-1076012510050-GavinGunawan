package com.gavin.wizzardadvanture

class Enemy(
    var type: String
) {
    var maxHp = 30
    var hp = 30

    fun getName(): String {
        return type + "mon"
    }
}