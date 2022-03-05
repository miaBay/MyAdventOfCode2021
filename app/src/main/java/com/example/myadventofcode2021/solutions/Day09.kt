package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myadventofcode2021.myOwnOptimizedSolutions.Day09MySolution
import com.example.myadventofcode2021.toddginsberg.Day09Solution

//https://adventofcode.com/2021/day/9
object Day09: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        val locationPoints = parseInput(input)
        var sumOfHeights = 0

        locationPoints.forEachIndexed { rIdx, row ->
            row.forEachIndexed { cIdx, current ->
                val up = if (rIdx > 0) locationPoints[rIdx -1][cIdx] else 0
                val prev = if (cIdx > 0) row[cIdx -1] else 0
                val next = if (cIdx < row.size -1) row[cIdx +1] else 0
                val down = if (rIdx < locationPoints.size -1) locationPoints[rIdx +1][cIdx] else 0

                sumOfHeights += when {
                    //first row
                    rIdx == 0
                            && cIdx == 0
                            && current < next
                            && current < down -> (current +1)
                    rIdx == 0
                            && current < prev
                            && current < next
                            && current < down -> (current +1)
                    rIdx == 0
                            && cIdx == row.size -1
                            && current < prev
                            && current < down -> (current +1)
                    //in between
                    cIdx == 0
                            && current < up
                            && current < next
                            && current < down -> (current +1)
                    current < prev
                            && current < up
                            && current < next
                            && current < down -> (current +1)
                    cIdx == row.size -1
                            && current < up
                            && current < prev
                            && current < down -> (current +1)
                    //last row
                    rIdx == locationPoints.size -1
                            && cIdx == 0
                            && current < up
                            && current < next -> (current +1)
                    rIdx == locationPoints.size -1
                            && current < prev
                            && current < up
                            && current < next -> (current +1)
                    rIdx == locationPoints.size -1
                            && cIdx == row.size -1
                            && current < prev
                            && current < up -> (current +1)
                    else -> 0
                }
            }
        }

        return sumOfHeights
    }

    override fun myOwnSolutionPart2(input: String): Any {
        val locationPoints = parseInput(input)
        val smallBasins = mutableListOf<Int>()
        val setOfPoints = mutableSetOf<Pair<Int, Int>>()

        for (rIdx in locationPoints.indices) {
            var sumOfCoordinates = 0
            for (cIdx in locationPoints[rIdx].indices) {
                val current = locationPoints[rIdx][cIdx]

                if (current == 9) continue

                val currentPair = rIdx to cIdx
                if (currentPair in setOfPoints) continue

                setOfPoints += currentPair

                val prev = locationPoints[rIdx].getOrNull(cIdx)
                //val up = if (rIdx > 0) locationPoints[rIdx -1][cIdx] else null
                val up = locationPoints.getOrNull(rIdx -1)?.get(cIdx) //check if this will return null
                val next = locationPoints[rIdx].getOrNull(cIdx +1)
                //val down = if (rIdx < locationPoints.size -1) locationPoints[rIdx +1][cIdx] else null
                val down = locationPoints.getOrNull(rIdx +1)?.get(cIdx) //check if this will return null

                if (prev != null && prev != 9) {
                    setOfPoints += rIdx to cIdx -1
                    sumOfCoordinates +=1
                }

                if (up != null && up != 9) {
                    setOfPoints += rIdx -1 to cIdx
                    sumOfCoordinates +=1
                }

                if (next != null && next != 9) {
                    setOfPoints += rIdx to cIdx +1
                    sumOfCoordinates +=1
                }

                if (down != null && down != 9) {
                    setOfPoints += rIdx +1 to cIdx
                    sumOfCoordinates +=1
                }
            }

            smallBasins += sumOfCoordinates
        }

        val smallest = smallBasins.minOf { it }
        smallBasins -= smallest

        return smallBasins.sumOf { it }
    }

    override fun optimizedSolutionPart1(input: String): Any {
        return Day09Solution(input.splitMultiline()).solvePart2()
    }

    override fun optimizedSolutionPart2(input: String): Any {
        return Day09MySolution().solvePart2(input.splitMultiline())
    }

    private fun parseInput(input: String): List<List<Int>> =
        input.splitMultiline().map { row ->
            row.split("").filter { it.isNotBlankOrEmpty() }.map(String::toInt)
        }
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day09.solve()
}