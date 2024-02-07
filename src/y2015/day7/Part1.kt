package y2015.day7

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 16:13
 * @Description:
 */
fun main() {
    val map = mutableMapOf<String,Int>()
    val data = File("src/y2015/day7/day7.txt").readLines().toMutableList()
    while (!data.isEmpty()) {
        data.iterator().let {
                iterator ->
            while (iterator.hasNext()) {
                val line = iterator.next()
                try {
                    if (line.contains("AND")) {
                        doAction(Action.AND,line,map)
                    } else if (line.contains("OR")) {
                        doAction(Action.OR,line,map)
                    } else if (line.contains("LSHIFT")) {
                        doAction(Action.LSHIFT,line,map)
                    } else if (line.contains("RSHIFT ")) {
                        doAction(Action.RSHIFT,line,map)
                    } else if (line.contains("NOT")) {
                        doAction(Action.NOT,line,map)
                    } else {
                        doAction(Action.SET,line,map)
                    }
                    iterator.remove()
                } catch (e:Exception) {
//                println("Error on line: $line")
                }
            }


        }
    }

    map.forEach {
        println(it)
    }
}

enum class Action {
    AND, OR, LSHIFT, RSHIFT, NOT, SET
}

fun doAction(action: Action,cmd:String,map:MutableMap<String,Int>) {
    val (input,output) = cmd.split(" -> ")
    val value = when (action) {
        Action.AND -> {
            val (a,b) = input.split(" AND ")
                a.getValue(map) and b.getValue(map)
        }
        Action.OR -> {
            val (a,b) = input.split(" OR ")
            a.getValue(map) or b.getValue(map)
        }
        Action.LSHIFT -> {
            val (a,b) = input.split(" LSHIFT ")
            a.getValue(map) shl b.toInt()
        }
        Action.RSHIFT -> {
            val (a,b) = input.split(" RSHIFT ")
            a.getValue(map) shr b.toInt()
        }
        Action.NOT -> {
            val a = input.split("NOT ")[1]
            a.getValue(map).inv()
        }
        Action.SET -> {
            input.getValue(map)
        }
    }
    map[output] = value
}

private fun String.getValue(map:Map<String,Int>):Int {
    toIntOrNull()?.let { return it } ?: return map[this]!!
}