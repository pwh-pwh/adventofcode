package y2015.day7

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 16:13
 * @Description:
 */
fun main() {
    val map = mutableMapOf<String,Int>()
    File("src/y2015/day7/day7.txt")
        .forEachLine {
            line ->
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
        }
}

enum class Action {
    AND, OR, LSHIFT, RSHIFT, NOT, SET
}

fun doAction(action: Action,cmd:String,map:Map<String,Int>) {

}