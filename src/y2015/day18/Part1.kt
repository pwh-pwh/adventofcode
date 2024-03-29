package y2015.day18

import java.io.File



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

    fun List<CharArray>.isOn(x: Int, y: Int): Boolean {
        val (w, h) = this[0].size to this.size
        return x in 0 until w && y in 0 until h && this[y][x] == '#'
    }

    fun List<CharArray>.getNeighborOnCount(x: Int, y: Int): Int {
        return matrix.count {
            this.isOn(x + it[0], y + it[1])
        }
    }

    fun List<CharArray>.doChangeState(): List<CharArray> {
        val (w, h) = this[0].size to this.size
        val new = this.map { it.clone() }.toMutableList()
        for (y in this.indices) {
            for (x in this[y].indices) {
                val count = this.getNeighborOnCount(x, y)
                new[y][x] = when {
                    this[y][x] == '#' && count in 2..3 -> '#'
                    this[y][x] == '.' && count == 3 -> '#'
                    else -> '.'
                }
            }
        }
        return new
    }

    fun List<CharArray>.countOn(): Int {
        return this.sumOf { it.count { it == '#' } }
    }

    var list = File("src/y2015/day18/input.txt").readLines().map { it.toCharArray() }

    repeat(100) {
        list = list.doChangeState()
    }
    println(list.countOn())
}