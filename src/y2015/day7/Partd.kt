package y2015.day7

import java.io.File

var wires = mutableMapOf<String, Int>()

fun valueOf(v: String): Int {
    return v.toIntOrNull() ?: wires[v]!!
}

fun parseExpression(ex: String): Int {
    val data = ex.trim().split(" ")
    return when {
        data.size == 1 -> valueOf(data[0])
        data.size == 2 -> valueOf(data[1]) xor 65535
        else -> {
            val (a, e, b) = data
            val aValue = valueOf(a)
            val bValue = valueOf(b)
            when (e) {
                "AND" -> aValue and bValue
                "OR" -> aValue or bValue
                "LSHIFT" -> aValue shl bValue
                "RSHIFT" -> aValue shr bValue
                else -> throw IllegalArgumentException("Unknown operation: $e")
            }
        }
    }
}

fun evaluate(line: String) {
    val (ex, n) = line.split(" -> ")
    if (n == "b") {
        wires[n] = 956
        return

    }
    wires[n] = parseExpression(ex)
}

fun main() {
    val data = File("src/y2015/day7/day7.txt").readLines().toMutableList()
    while (data.isNotEmpty()) {
        data.iterator().let { iterator ->
            while (iterator.hasNext()) {
                val d = iterator.next()
                try {
                    evaluate(d)
                    iterator.remove()
                } catch (e: Exception) {
                    continue
                }
            }
        }
    }
    println(wires["a"])
}