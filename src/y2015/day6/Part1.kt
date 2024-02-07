package y2015.day6

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 15:16
 * @Description:
 */
fun main() {
    var grid = Array(1000) { BooleanArray(1000) { false } }
    File("src/y2015/day6/day6.txt").forEachLine { it ->
        if (it.startsWith("turn on ")) {
            doChangeGrid(grid, it.removePrefix("turn on ")) { true }
        } else if (it.startsWith("toggle ")) {
            doChangeGrid(grid, it.removePrefix("toggle "), { !it })
        } else {
            doChangeGrid(grid, it.removePrefix("turn off ")) { false }
        }
    }
    val count = grid.sumOf {
        it.count {
            flag -> flag
        }
    }
    println(count)
}

private fun getTwoPoints(it: String):Pair<String,String>  {
    val (p1,p2) = it.split(" through ")
    return Pair(p1,p2)
}

private fun doChangeGrid(grid: Array<BooleanArray>, cmd:String,gf:(Boolean) -> Boolean) {
    val (p1,p2) = getTwoPoints(cmd)
    val (x1,y1) = p1.split(",").map { it.toInt() }
    val (x2,y2) = p2.split(",").map { it.toInt() }
    for(x in x1..x2) {
        for(y in y1..y2) {
            grid[x][y] = gf(grid[x][y])
        }
    }
}