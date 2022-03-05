package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day06.solve()
}

object Day06: Solution() {

    private const val numberOfDays = 80

    override fun myOwnSolutionPart1(input: String): Any {
        val inputs = input
            .split(",")
            .map(String::toInt)
            .toMutableList()

        val llist = mutableListOf<MutableList<Int>>()

        for (num in inputs) {
            llist += mutableListOf(num)
        }

        for (i in 0 until numberOfDays) {
            for (list in llist) {

                val iterator = list.listIterator()

                var zeroCount = 0
                while (iterator.hasNext()) {
                    val current = iterator.next()
                    if (current > 0) iterator.set(current -1)
                    else {
                        iterator.set(6)
                        zeroCount++
                    }
                }

                if (zeroCount > 0) {
                    for (ii in 0 until zeroCount) {
                        list += 8
                    }
                }
            }
        }

        var totalCount = 0

        for (list in llist) {
            totalCount += list.size
        }

        return totalCount
    }

    //can't overcome the heap issue
    //02/13/2022
    override fun myOwnSolutionPart2(input: String): Any {
        /*val inputs = "3,4,3,1,2"
            .split(",")
            .map(String::toInt)

        val hmList = hashMapOf<Int, MutableList<Int>>()
        val hmMultiplier = hashMapOf<Int, Int>()

        for (num in inputs) {
            if (!hmList.containsKey(num)) {
                hmList[num] = mutableListOf(num)
                continue
            }

            var current = hmMultiplier[num] ?: 1
            hmMultiplier[num] = current +1
        }

        for (i in 0 until 256) {
            for ((_, list) in hmList) {

                val iterator = list.listIterator()

                var zeroCount = 0
                while (iterator.hasNext()) {
                    val current = iterator.next()

//                    if (hmMultiplier.containsKey(current)) {
//                        var temp = hmMultiplier[current] ?: 1
//                        hmMultiplier[current] = temp +1
//                        continue
//                    }

                    if (current > 0) iterator.set(current -1)
                    else {
                        iterator.set(6)
                        zeroCount++
                    }
                }

                if (zeroCount > 0) {
                    for (ii in 0 until zeroCount) {
                        list += 8
                    }
                }
            }
        }

        var totalCount = 0
        for ((key, list) in hmList) {
            var size = list.size

            if (hmMultiplier.containsKey(key)) {
                size *= hmMultiplier[key]!!
            }

            totalCount += size
        }

        return totalCount*/

        val inputs = parseInt(input)
        val map: Map<Int, Int> = inputs.groupBy { it }.mapValues { (_, value) -> value.count() }
        val map1 = inputs.groupBy { it }
        //val map2: Map<Int, Int> = inputs.groupingBy { it }
        println("printing map of int int $map")
        println("printing group of ints $map1")
        println("printing map of int int using groupingBy ${inputs.groupingBy { it }.eachCount()}")

        return -1
    }

    //my own optimization
    //still, this implementation can't overcome heap problem
    override fun optimizedSolutionPart1(input: String): Any {
        val inputs = input
            .split(",")
            .map(String::toInt)
            .toMutableList()

        for (i in 0 until 80) {
            val list = mutableListOf<Int>()

            val iterator = inputs.listIterator()
            while (iterator.hasNext()) {
                val current = iterator.next()
                if (current > 0) iterator.set(current -1)
                else {
                    iterator.set(6)
                    list += 8
                }
            }

            inputs += list
        }

        return inputs.size
    }

    override fun optimizedSolutionPart2(input: String): Any {
        return determineFishPopulation(parseInt(input), 256)
    }

    private fun determineFishPopulation(fishCount: List<Int>, days: Int): ULong {
        var fishToSize: Map<Int, ULong> = fishCount.countBy { it }.mapValues { it.value.toULong() }

        repeat(days) { day ->
            val flatMap: List<Pair<Int, ULong>> = fishToSize
                .flatMap { (timeUntilProcreation, size) ->
                    if (timeUntilProcreation == 0)
                        return@flatMap listOf(6 to size, 8 to size)
                    listOf((timeUntilProcreation -1) to size)
                }

            val groupBy: Map<Int, List<ULong>> = flatMap
                .groupBy({ it.first }, { it.second })

            fishToSize = groupBy
                .mapValues { (_, uLongList) -> uLongList.sum() }

            //println("Population reached ${fishToSize.values.sum()} fish(es) after day ${day +1}")
        }

        return fishToSize.values.sum()
    }

    private fun parseInt(input: String): List<Int> {
        return input.split(",").map(String::toInt)
    }
}