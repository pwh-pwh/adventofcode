package y2015.day21

import kotlin.math.max


fun main() {
    val losses = mutableListOf<Int>()

    for ((weaponCost, weaponDamage, _) in weapons) {
        for ((armorCost, _, armorArmor) in armor) {
            for (combination in rings.combinations(2)) {
                val ring1 = combination[0]
                val ring2 = combination[1]
                val player = mutableListOf(100, weaponDamage + ring1.second + ring2.second, armorArmor + ring1.third + ring2.third)
                if (!doFight(player)) {
                    losses.add(weaponCost + armorCost + ring1.first + ring2.first)
                }
            }
        }
    }

    println(losses.maxOrNull())
}