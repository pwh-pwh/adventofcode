package y2015.day15

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/7 16:28
 * @Description:
 */
fun main() {
    data class Record(val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int)
    var data = File("src/y2015/day15/day15.txt")
        .readLines()
        .map {
            str ->
            val numberRegex = """\d+""".toRegex()
            val (capacity, durability, flavor,texture,calories) = numberRegex.findAll(str).map {
                it.value.toInt()
            }.toList()
            Record(capacity, durability, flavor, texture, calories)
        }

}