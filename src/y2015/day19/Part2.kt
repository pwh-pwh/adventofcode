package y2015.day19

import java.io.File
import kotlin.streams.toList

fun main() {
    var mValue = ""
    var input = File("src/y2015/day19/input.txt").useLines { lines ->
        lines.map {
            if (!it.trim().contains("=>")) {
                mValue = it
            }
            it.split(" => ") }.filter {
            it.size == 2
        }.toList()
    }

    var molecule = mValue

    var count2 = 0
    while (molecule != "e") {
        input = input.shuffled()

        while (molecule != "e") {
            var pm = molecule
            for (each in input) {
                if (molecule.contains(each[1])) {
                    molecule = molecule.replaceFirst(each[1],each[0])
                    count2++
                }
            }
            if (pm == molecule) {
                molecule = mValue
                count2 = 0
                break
            }
        }

    }
    println(count2)
}