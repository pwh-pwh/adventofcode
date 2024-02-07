package y2015.day5

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 14:28
 * @Description:
 */
fun main() {
    val count = File("src/y2015/day5/day5.txt").readLines().count { it.isNice() }
    println(count)
}

private fun String.isNice(): Boolean {
    val hasThreeVowels = count { it in "aeiou" } >= 3
    val hasDoubleLetter = zipWithNext().any { (a, b) -> a == b }
    val hasNoBadSubstrings = listOf("ab", "cd", "pq", "xy").none { this.contains(it) }

    return hasThreeVowels && hasDoubleLetter && hasNoBadSubstrings
}