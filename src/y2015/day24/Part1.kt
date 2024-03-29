package y2015.day24

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val input = "src/y2015/day24/input.txt"
    val startTime = System.currentTimeMillis()

    val lines = Files.readAllLines(Paths.get(input))
    val list = lines.map { it.toInt() }
    val sum = list.sum()

    // for part 1 use 3, part 2 use 4
    val each = sum / 4

    val sortedList = list.sorted()
    println("${sortedList.size}, $sum, $each")
    println(sortedList)
    var minV = Long.MAX_VALUE
    for (i in sortedList.indices) {
        val cp = ArrayList(sortedList)
        cp.removeAt(i)
        val filled = fill(cp, 0, each)
        val lFilled = filled.map { it.toLong() }
        val product = lFilled.fold(1L) { acc, num -> acc * num }
        println("product $product")
        if(product<minV) {
            minV = product
        }
    }
    println("minV: $minV")

    val endTime = System.currentTimeMillis()
    println("Time ${endTime - startTime}ms")
}

fun fill(list: List<Int>, bucket: Int, max: Int): List<Int> {
    var currentBucket = bucket
    val nums = mutableListOf<Int>()

    for (i in list.indices.reversed()) {
        if (currentBucket + list[i] <= max) {
            currentBucket += list[i]
            nums.add(list[i])
        }
    }

    return nums
}