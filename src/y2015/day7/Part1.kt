package y2015.day7

import java.io.File

fun main() {
    val map = mutableMapOf<String, Int>()
    val data = File("src/y2015/day7/day7.txt").readLines().toMutableList()

    while (data.isNotEmpty()) {
        data.iterator().let { iterator ->
            while (iterator.hasNext()) {
                val line = iterator.next()
                try {
                    val action = when {
                        line.contains("AND") -> Action.AND
                        line.contains("OR") -> Action.OR
                        line.contains("LSHIFT") -> Action.LSHIFT
                        line.contains("RSHIFT") -> Action.RSHIFT
                        line.contains("NOT") -> Action.NOT
                        else -> Action.SET
                    }
                    doAction(action, line, map)
                    iterator.remove()
                } catch (e: Exception) {
                    // Ignore errors
                }
            }
        }
    }

    map.forEach { println(it) }
}

enum class Action {
    AND, OR, LSHIFT, RSHIFT, NOT, SET
}

fun doAction(action: Action, cmd: String, map: MutableMap<String, Int>) {
    val (input, output) = cmd.split(" -> ")
    val value = when (action) {
        Action.AND -> input.split(" AND ").run { this[0].getValue(map) and this[1].getValue(map) }
        Action.OR -> input.split(" OR ").run { this[0].getValue(map) or this[1].getValue(map) }
        Action.LSHIFT -> input.split(" LSHIFT ").run { this[0].getValue(map) shl this[1].toInt() }
        Action.RSHIFT -> input.split(" RSHIFT ").run { this[0].getValue(map) shr this[1].toInt() }
        Action.NOT -> input.split("NOT ")[1].getValue(map).inv()
        Action.SET -> input.getValue(map)
    }
    map[output] = value
}

private fun String.getValue(map: Map<String, Int>): Int = this.toIntOrNull() ?: map[this]!!