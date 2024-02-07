package y2015.day6

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 15:16
 * @Description:
 */
fun main() {
    var grid = Array(1000) { IntArray(1000) { 0 } }
    File("src/y2015/day6/day6.txt").forEachLine { it ->
        if (it.startsWith("turn on ")) {
            doChangeGrid(grid, it.removePrefix("turn on ")) { prv ->
                prv + 1
            }
        } else if (it.startsWith("toggle ")) {
            doChangeGrid(grid, it.removePrefix("toggle ")) { prv ->
                prv + 2
            }
        } else {
            doChangeGrid(grid, it.removePrefix("turn off ")) { prv ->
                if (prv == 0) 0 else prv - 1
            }
        }
    }
    val count = grid.sumOf {
        it.sum()
    }
    println(count)
}

private fun getTwoPoints(it: String): Pair<String, String> {
    val (p1, p2) = it.split(" through ")
    return Pair(p1, p2)
}

private fun doChangeGrid(grid: Array<IntArray>, cmd: String, gf: (Int) -> Int) {
    val (p1, p2) = getTwoPoints(cmd)
    val (x1, y1) = p1.split(",").map { it.toInt() }
    val (x2, y2) = p2.split(",").map { it.toInt() }
    for (x in x1..x2) {
        for (y in y1..y2) {
            grid[x][y] = gf(grid[x][y])
        }
    }
}