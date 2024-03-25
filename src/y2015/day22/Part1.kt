package y2015.day22

/**
 * @Auther: coderpwh
 * @Date: 2024/3/25 16:30
 * @Description:
 */
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Comparator
import java.util.PriorityQueue
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.min

fun main() {
    val s = File("src/y2015/day22/input.txt").readLines()
    var start = System.currentTimeMillis()
    println("Part One = " + solve(s, false))
    println(System.currentTimeMillis() - start)
    start = System.currentTimeMillis()
    println("Part Two = " + solve(s, true))
    println(System.currentTimeMillis() - start)
}

fun solve(s: List<String>, hard: Boolean): Int {
    val wizards = PriorityQueue<Wizard>(Comparator { a, b -> b.manaSpend.compareTo(a.manaSpend) })
    val minMana = AtomicInteger(Int.MAX_VALUE)
    wizards.add(Wizard(50, 500, parseBoss(s)))
    while (wizards.isNotEmpty()) {
        val curr = wizards.poll()
        if (hard) {
            if (curr.hp-- <= 0)
                continue
        }
        curr.applyEffect()
        for (spell in Wizard.spells.indices) {
            if (curr.canCast(spell)) {
                val next = curr.clone()
                next.castSpell(spell)
                next.applyEffect()

                if (next.boss[0] <= 0) {
                    minMana.set(min(minMana.get(), next.manaSpend))
                    wizards.removeIf { w -> w.manaSpend > minMana.get() }
                } else {
                    next.hp -= maxOf(1, next.boss[1] - next.armor)
                    if (next.hp > 0 && next.mana > 0 && next.manaSpend < minMana.get())
                        wizards.add(next)
                }
            }
        }
    }
    return minMana.get()
}

fun parseBoss(s: List<String>): IntArray {
    return IntArray(2) { i -> s[i].split(": ")[1].toInt() }
}

data class Wizard(
    var hp: Int,
    var mana: Int,
    var boss: IntArray,
    var armor: Int = 0,
    var manaSpend: Int = 0,
    var activeEffects: IntArray = IntArray(3)
) : Comparable<Wizard> {
    companion object {
        val spells = arrayOf(
            intArrayOf(53, 0), intArrayOf(73, 0), intArrayOf(113, 6),
            intArrayOf(173, 6), intArrayOf(229, 5)
        )
    }

    fun canCast(i: Int): Boolean {
        return mana >= spells[i][0] && (i < 2 || activeEffects[i - 2] == 0)
    }

    fun castSpell(i: Int) {
        mana -= spells[i][0]
        manaSpend += spells[i][0]
        when (i) {
            0 -> boss[0] -= 4
            1 -> {
                hp += 2
                boss[0] -= 2
            }
            else -> activeEffects[i - 2] = spells[i][1]
        }
    }

    fun applyEffect() {
        for (i in activeEffects.indices) {
            if (activeEffects[i] > 0) {
                activeEffects[i]--
                when (i) {
                    0 -> armor = 7
                    1 -> boss[0] -= 3
                    2 -> mana += 101
                }
            } else if (i == 0) armor = 0
        }
    }

    fun clone(): Wizard {
        return Wizard(hp, mana, boss.copyOf(), armor, manaSpend, activeEffects.copyOf())
    }

    override fun compareTo(other: Wizard): Int {
        return other.manaSpend - this.manaSpend
    }
}