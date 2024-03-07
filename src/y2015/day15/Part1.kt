package y2015.day15

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/7 16:28
 * @Description:
 */
fun main() {
    var data = File("src/y2015/day15/day15.txt")
        .readLines()
        .map {
            str ->
            val numberRegex = """-?\d+""".toRegex()
            val (capacity, durability, flavor,texture,calories) = numberRegex.findAll(str).map {
                it.value.toInt()
            }.toList()
            Record(capacity, durability, flavor, texture, calories)
        }
    doCal(data, mutableListOf(),100)
    println("max: $maxValue")
}
var maxValue = Int.MIN_VALUE
data class Record(val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int)

private fun doCal(data: List<Record>,dis:MutableList<Int>,t:Int) {
    if(data.size == dis.size+1) {
        dis.add(t)
        var calR = calR(data, dis)
        if (calR > maxValue) {
            maxValue = calR
        }

        dis.removeLast()
        return
    }
    for (i in 0..t) {
        dis.add(i)
        doCal(data, dis, t - i)
        dis.removeAt(dis.size - 1)
    }
}

fun calR(data: List<Record>,dis:List<Int>):Int {
    var capacity = 0
    var durability = 0
    var flavor = 0
    var texture = 0
    data.zip(dis)
        .forEach {
            (record, dis) ->
            capacity += record.capacity * dis
            durability += record.durability * dis
            flavor += record.flavor * dis
            texture += record.texture * dis
        }
    if (capacity<=0||durability<=0||flavor<=0||texture<=0) return 0
    return capacity*durability*flavor*texture
}