package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day07.solve()
}

object Day07: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        val inputs = input
            .split(",")
            .map(String::toInt)

        var finalFuelLeft = Int.MAX_VALUE

        repeat(inputs.size) { i ->
            var fuelLeft = 0
            inputs.forEach { num ->
                fuelLeft += abs(num - (i + 1))
            }

            if (fuelLeft < finalFuelLeft) finalFuelLeft = fuelLeft
        }

        return finalFuelLeft
    }

    override fun myOwnSolutionPart2(input: String): Any {
        val inputs = input
            .split(",")
            .map(String::toInt)

        var finalFuelLeft = Int.MAX_VALUE
        repeat(inputs.size) { i ->
            var fuelLeft = 0
            inputs.forEach { num ->
                val steps = abs(num -(i +1))
                repeat(steps) { step ->
                    fuelLeft += (step +1)
                }
            }

            if (fuelLeft < finalFuelLeft) finalFuelLeft = fuelLeft
        }

        return finalFuelLeft
    }

    //my own solution
    override fun optimizedSolutionPart1(input: String): Any {
        val inputs = input
            .split(",")
            .map(String::toInt)

////    around 55~80 milliseconds
        var finalFuelLeft = Int.MAX_VALUE
        repeat(inputs.size) { i ->
            val fuelLeft = inputs.sumOf { abs(it - (i +1)) }
            if (fuelLeft < finalFuelLeft) finalFuelLeft = fuelLeft
        }

        return finalFuelLeft

////    around 140~150 milliseconds
////    return calculateFuelCost(inputs) { it }

        //return com.example.myadventofcode2021.toddginsberg.Day07(input).solvePart1()
    }

    override fun optimizedSolutionPart2(input: String): Any {

        /*
        THIS SOLUTION IS FROM THIS: https://www.youtube.com/c/TheSelfTaughtSoftwareEngineer

        val crabPositions: List<Int> = input.split(",").toIntList()
        val fuelPerPosition: Map<Int, Long> = calculateFuelConsumptionPerPosition(crabPositions) { start, end ->
            abs(start - end).toLong().triangular()
        }

        return fuelPerPosition.minOf { it.value }*/

        //GOT THE INSPIRATION FROM THE EXPLANATION AND THIS "TRIANGULAR NUMBER(S)"
        //HERE IS MY OWN OPTIMIZED SOLUTION FOR PART 2

        val inputs = input
            .split(",")
            .map(String::toInt)

        ////v my solution
        //// faster than the current implementation
        //// the reason it is being used is to test passing function as parameter
////    around 66~89 milliseconds
        var finalFuelLeft = Int.MAX_VALUE
        repeat(inputs.size) { i ->
            var fuelLeft = inputs.sumOf { abs(it -(i +1)).triangular() }
            if (fuelLeft < finalFuelLeft) finalFuelLeft = fuelLeft
        }

        return finalFuelLeft
        ////^ my solution

        /**
         * even though this one is still faster than the first implementation(myOwnSolutionPart2),
         * my code above is way faster, ranging from 81~89 milliseconds...
         * */
////    return calculateFuelCost(inputs) { fuel -> fuel.triangular() }

        //return com.example.myadventofcode2021.toddginsberg.Day07(input).solvePart2()
    }

    private fun calculateFuelConsumptionPerPosition(
        startingPositions: List<Int>,
        fuelEquation: (Int, Int) -> Long
    ): Map<Int, Long> {
        val min = startingPositions.minOf { it }
        val max = startingPositions.maxOf { it }
        return (min..max).associateWith { end ->
            startingPositions.sumOf { start ->
                fuelEquation(start, end)
            }
        }
    }

    private fun calculateFuelCost(list: List<Int>, fuelEquation: (Int) -> Int): Int =
        (0..list.size).associateWith { index ->
            list.sumOf { fuelEquation(abs(it - (index +1))) }
        }.minOf { it.value }
}