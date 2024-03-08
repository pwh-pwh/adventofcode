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
    val rm = mutableMapOf<Int,Int>()

    fun countCombinations(list: List<Int>, r: Int): Int {
        fun backtrack(start: Int, total: Int,ct:Int): Int {
            if (total == r) {
                rm[ct] = rm.getOrDefault(ct,0)+1
                return 1
            }
            if (total > r) {
                return 0
            }
            var count = 0
            for (i in start until list.size) {
                count += backtrack(i + 1, total + list[i],ct+1)
            }
            return count
        }
        return backtrack(0, 0,0)
    }
    println(countCombinations(list, 150))
}