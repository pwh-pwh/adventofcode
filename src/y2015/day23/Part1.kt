package y2015.day23

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/28 15:45
 * @Description:
 */
sealed class Instruction

data class Hlf(val register: String) : Instruction()

data class Tpl(val register: String) : Instruction()

data class Inc(val register: String) : Instruction()

data class Jmp(val offset: Int) : Instruction()

data class Jie(val register: String, val offset: Int) : Instruction()

data class Jio(val register: String, val offset: Int) : Instruction()

object Rm {
    var a = 1L
    var b = 0L
    fun setV(r:String,v:Long) {
        if (r == "a") {
            a  = v
        } else {
           b = v
        }
    }

    fun getV(r:String):Long {
        return if (r == "a") {
            a
        } else {
            b
        }
    }
}

fun main() {
    val itList = File("src/y2015/day23/input.txt").readLines()
        .map {
            when {
                it.startsWith("hlf") -> Hlf(it.split(" ")[1])
                it.startsWith("tpl") -> Tpl(it.split(" ")[1])
                it.startsWith("inc") -> Inc(it.split(" ")[1])
                it.startsWith("jmp") -> Jmp(it.split(" ")[1].toInt())
                it.startsWith("jie") -> Jie(it.split(" ")[1].split(",")[0], it.split(", ")[1].toInt())
                it.startsWith("jio") -> Jio(it.split(" ")[1].split(",")[0], it.split(", ")[1].toInt())
                else -> throw Exception("can not parse instruction")
            }
        }
//    doIns(0,itList)
    doInsFun(itList)
    println(Rm.b)
}

fun doInsFun(itList:List<Instruction>) {
    var position = 0
    while (position<itList.size && position>=0) {
        var instruction = itList.get(position)
        println("position: $position a: ${Rm.a}  b: ${Rm.b}")
        when (instruction) {
            is Hlf -> {
                Rm.setV(instruction.register,Rm.getV(instruction.register)/2)
                position+=1
            }

            is Inc -> {
                Rm.setV(instruction.register,Rm.getV(instruction.register)+1)
                position+=1
            }

            is Jie -> {
                if (Rm.getV(instruction.register) % 2 == 0L) {
                    position = position + instruction.offset
                } else {
                    position += 1
                }
            }

            is Jio -> {
                if (Rm.getV(instruction.register) == 1L) {
                    position = position + instruction.offset
                } else {
                    position+=1
                }
            }

            is Jmp -> {
                position = position + instruction.offset
            }

            is Tpl -> {
                Rm.setV(instruction.register,Rm.getV(instruction.register)*3)
                position+=1
            }
        }
    }
}

fun doIns(position: Int, itList: List<Instruction>) {
    if (position >= itList.size) {
        return
    }
    var instruction = itList.get(position)
    when (instruction) {
        is Hlf -> {
            Rm.setV(instruction.register,Rm.getV(instruction.register)/2)
            doIns(position + 1, itList)
        }

        is Inc -> {
            Rm.setV(instruction.register,Rm.getV(instruction.register)+1)
            doIns( position + 1, itList)
        }

        is Jie -> {
            Rm.getV(instruction.register)
            if (Rm.getV(instruction.register) % 2 == 0L) {
                doIns( position + instruction.offset, itList)
            } else {
                doIns( position + 1, itList)
            }
        }

        is Jio -> {
            if (Rm.getV(instruction.register) == 1L) {
                doIns( position + instruction.offset, itList)
            } else {
                doIns(position + 1, itList)
            }
        }

        is Jmp -> {
            doIns(position + instruction.offset, itList)
        }

        is Tpl -> {
            Rm.setV(instruction.register,Rm.getV(instruction.register)*3)
            doIns(position + 1, itList)
        }
    }
}