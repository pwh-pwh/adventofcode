package y2015.day21

import kotlin.math.max

val weapons = setOf(
    Triple(8, 4, 0),
    Triple(10, 5, 0),
    Triple(25, 6, 0),
    Triple(40, 7, 0),
    Triple(74, 8, 0)
)

val armor = setOf(
    Triple(0, 0, 0),
    Triple(13, 0, 1),
    Triple(31, 0, 2),
    Triple(53, 0, 3),
    Triple(75, 0, 4),
    Triple(102, 0, 5)
)

val rings = setOf(
    Triple(0, 0, 0),
    Triple(0, 0, 0),
    Triple(25, 1, 0),
    Triple(50, 2, 0),
    Triple(100, 3, 0),
    Triple(20, 0, 1),
    Triple(40, 0, 2),
    Triple(80, 0, 3)
)

fun doFight(player: MutableList<Int>): Boolean {
    val boss = mutableListOf(109, 8, 2)
    while (true) {
        boss[0] -= max(player[1] - boss[2], 1)
        if (boss[0] <= 0) return true
        player[0] -= max(boss[1] - player[2], 1)
        if (player[0] <= 0) return false
    }
}

fun main() {
    val wins = mutableListOf<Int>()

    for ((weaponCost, weaponDamage, _) in weapons) {
        for ((armorCost, _, armorArmor) in armor) {
            for (combination in rings.combinations(2)) {
                val ring1 = combination[0]
                val ring2 = combination[1]
                val player = mutableListOf(100, weaponDamage + ring1.second + ring2.second, armorArmor + ring1.third + ring2.third)
                if (doFight(player)) {
                    wins.add(weaponCost + armorCost + ring1.first + ring2.first)
                }
            }
        }
    }

    println(wins.minOrNull())
}

/**
 * Extension function to get all combinations of a Set of size n.
 */
fun <T> Set<T>.combinations(n: Int): Set<List<T>> {
    if (n == 0) return setOf(emptyList())
    if (n >= size) return setOf(toList())
    if (n == 1) return map { listOf(it) }.toSet()

    return flatMap { element ->
        minus(element).combinations(n - 1).map { combination ->
            listOf(element) + combination
        }
    }.toSet()
}