package y2015.day17

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/8 16:47
 * @Description:
 */
fun main() {
    val list = File("src/y2015/day17/input.txt")
        .readLines()
        .map {
            it.toInt()
        }.toList()

    fun countCombinations(list: List<Int>, r: Int): Int {
        fun backtrack(start: Int, total: Int): Int {
            if (total == r) {
                return 1
            }
            if (total > r) {
                return 0
            }
            var count = 0
            for (i in start until list.size) {
                count += backtrack(i + 1, total + list[i])
            }
            return count
        }
        return backtrack(0, 0)
    }
    println(countCombinations(list, 150))
}