package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi

//https://adventofcode.com/2021/day/8
object Day08: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        val generatedSevenSegmentDisplays = input
            .splitMultiline()
            .map {
                it.substring(it.indexOf("|") +2)
            }.map { it.split(" ").map { it.length } }

        //2 is for 1
        //3 is for 7
        //4 is for 4
        //7 is for 8
        val filteredSevenSegments = listOf(2, 3, 4, 7)

        var numberOfFilteredSegments = 0
        generatedSevenSegmentDisplays.forEach { codes ->
            codes.forEach { code ->
                if (filteredSevenSegments.contains(code)) numberOfFilteredSegments++
            }
        }

        return numberOfFilteredSegments
    }

    override fun myOwnSolutionPart2(input: String): Any {
        /*val generatedSevenSegmentDisplays = input
            .splitMultiline()
            .map {
                it.substring(it.indexOf("|") +2)
            }.map { it.split(" ") }

        val hmSevenSegments = mapOf(
            "cagedb" to 0,
            "gcdfa" to 2,
            "fbcad" to 3,
            "cdfbe" to 5,
            "cdfgeb" to 6,
            "cefabd" to 9
        )

        var sum = 0

        generatedSevenSegmentDisplays.forEach { codes ->
            var stringedNumber = ""
            codes.forEach { code ->
                stringedNumber += findEquivalentNumber(hmSevenSegments, code)
            }

            sum += stringedNumber.toInt()
        }


        return sum*/

        return -1
    }

    override fun optimizedSolutionPart1(input: String): Any {
        /*val generatedSevenSegmentDisplays = input
            .splitMultiline()
            .map {
                it.substring(it.indexOf("|") +2)
            }.map { it.split(" ") }

        //2 is for 1
        //3 is for 7
        //4 is for 4
        //7 is for 8
        val filteredSevenSegments = listOf(2, 3, 4, 7)

        var numberOfFilteredSegments = 0
        generatedSevenSegmentDisplays.forEach { codes ->
            codes.forEach { code ->
                if (filteredSevenSegments.contains(code.length)) numberOfFilteredSegments++
            }
        }*/

        val generatedSevenSegmentDisplays = input
            .splitMultiline()
            .map {
                it.substring(it.indexOf("|") +2)
            }
            .map {
                it.split(" ").map { it.length }
            }
            .flatten()

        //2 is for 1
        //3 is for 7
        //4 is for 4
        //7 is for 8
        val filteredSevenSegments = listOf(2, 3, 4, 7)

        var numberOfFilteredSegments = 0
        generatedSevenSegmentDisplays.forEach { codes ->
            if (filteredSevenSegments.contains(codes)) numberOfFilteredSegments++
        }

        return numberOfFilteredSegments
    }

    override fun optimizedSolutionPart2(input: String): Any {
        val day8Solution = com.example.myadventofcode2021.toddginsberg.Day08(input.splitMultiline())
        return day8Solution.solvePart2()
    }

    private fun findEquivalentNumber(map: Map<String, Int>, code: String): Int {
        val codeLength = code.length
        if (codeLength in listOf(2, 3, 4, 7)) {
            return when (codeLength) {
                2 -> 1
                3 -> 7
                7 -> 8
                else -> 4
            }
        }

        map.keys.forEach { key ->
            val sortedKey = String(key.toCharArray()).sortAlphabetically()
            val sortedCode = code.sortAlphabetically()
            if (sortedKey == sortedCode) {
                return map[key]!!
            }
        }

        return -1
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day08.solve()
}