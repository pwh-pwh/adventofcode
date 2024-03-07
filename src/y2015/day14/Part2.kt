package y2015.day14

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/5 16:05
 * @Description:
 */
private enum class State { RUNNING, RESTING }

fun main() {
    data class Reindeer(val speed: Int, val runTime: Int, val restTime: Int)
    data class Record(var distance: Int, var duration: Int, var count: Int = 0, var state: State = State.RUNNING)

    var data = File("src/y2015/day14/day14.txt").readLines().map { str ->
        val numberRegex = """\d+""".toRegex()
        val (speed, runTime, restTime) = numberRegex.findAll(str).map {
            it.value.toInt()
        }.toList()
        Reindeer(speed, runTime, restTime) to Record(0, runTime)
    }
    var time = 0
    while (time < 2503) {
        data.forEach { (reindeer, record) ->
            when (record.state) {
                State.RUNNING -> {
                    record.distance += reindeer.speed
                    if (--record.duration == 0) {
                        record.state = State.RESTING
                        record.duration = reindeer.restTime
                    }
                }

                State.RESTING -> {
                    if (--record.duration == 0) {
                        record.state = State.RUNNING
                        record.duration = reindeer.runTime
                    }
                }
            }
        }
        val maxD = data.maxOf {
            it.second.distance
        }
        data.filter {
            it.second.distance == maxD
        }.forEach {
            it.second.count += 1
        }
        time++
    }
    val maxD = data.maxOf {
        it.second.count
    }
    println("max:${maxD}")
}
