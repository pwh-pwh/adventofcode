package y2015.day18

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/15 10:14
 * @Description:
 */
fun main() {
    val matrix = listOf(
        intArrayOf(-1, 0),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(-1, -1),
        intArrayOf(-1, 1),
        intArrayOf(1, -1),
        intArrayOf(1, 1)
    )

    fun isOn(list: List<CharArray>, x: Int, y: Int): Boolean {
        val (w, h) = list[0].size to list.size
        if (x >= 0 && x < w && y >= 0 && y < h) {
            return list[y][x] == '#'
        }
        return false
    }

    fun List<CharArray>.getNeighborOnCount(x: Int, y: Int): Int {
        return matrix.count {
            isOn(this, x + it[0], y + it[1])
        }
    }

    fun List<CharArray>.doChangeState(): List<CharArray> {
        val (w, h) = this[0].size to this.size
        val new = this.map { it.clone() }.toMutableList()
        for (y in 0 until h) {
            for (x in 0 until w) {
                val count = this.getNeighborOnCount(x, y)
                if (this[y][x] == '#') {
                    if (count == 2 || count == 3) {
                        new[y][x] = '#'
                    } else {
                        new[y][x] = '.'
                    }
                } else {
                    if (count == 3) {
                        new[y][x] = '#'
                    } else {
                        new[y][x] = '.'
                    }
                }
            }
        }
        return new
    }

    fun List<CharArray>.countOn(): Int {
        return this.sumOf {
            it.count { it == '#' }
        }
    }

    var list = File("src/y2015/day18/input.txt").readLines().map { it.toCharArray() }

    repeat(100) {
        list = list.doChangeState()
    }
    println(list.countOn())
}
