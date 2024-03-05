package y2015.day14

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/5 16:05
 * @Description:
 */
fun main() {
    /**
     * Calculates the distance traveled by a reindeer in a given time frame.
     * @param speed the speed of the reindeer
     * @param runTime the duration the reindeer can run
     * @param restTime the duration the reindeer rests
     * @param totalTime the total time for the calculation
     * @return the distance traveled by the reindeer
     */
    fun calculateDistance(speed: Int, runTime: Int, restTime: Int, totalTime: Int): Int {
        var count = 0
        var distance = 0
        var t = totalTime
        while (t > 0) {
            t -= if (count % 2 == 0) {
                if (t <= runTime) {
                    distance += (t * speed)
                    break
                }
                distance += (runTime * speed)
                runTime
            } else {
                if (t <= restTime) {
                    break
                }
                restTime
            }
            count++
        }
        return distance
    }

    val max = File("src/y2015/day14/day14.txt").readLines()
        .maxOf { str ->
            val numberRegex = """\d+""".toRegex()
            val (speed, runTime, restTime) = numberRegex.findAll(str).map {
                it.value.toInt()
            }.toList()
            calculateDistance(speed, runTime, restTime, 2503)
        }
    println("max is $max")
}
