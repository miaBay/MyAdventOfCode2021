package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day03.solve()
}

object Day03: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        var list = input.splitMultiline()
        var gamma = ""
        var epsilon = ""

        for (x in 0 until list[0].length) {
            var oneCounter = 0
            var zerCounter = 0

            list.forEach { value ->
                if (value[x] == '1') oneCounter++
                else zerCounter++
            }

            gamma += if (oneCounter > zerCounter) "1" else "0"
        }

        gamma.forEach {
            epsilon += if (it == '1') "0" else "1"
        }

        return gamma.toBase10() * epsilon.toBase10()
    }

    override fun myOwnSolutionPart2(input: String): Any {
        var oxygen: String = findMostCommon(input.splitMultiline() as MutableList)
        var co2Scrubber: String = findLeastCommon(input.splitMultiline() as MutableList)

        println("oxygen count: ${oxygen.toBase10()}")
        println("co2 count: ${co2Scrubber.toBase10()}")

        return oxygen.toBase10() * co2Scrubber.toBase10()
    }

    private fun findMostCommon(list: MutableList<String>): String {
        val length = list[0].length
        for (x in 0 until length) {
            var oneCounter = 0
            var zerCounter = 0

            if (list.size == 1) return list[0]

            list.forEach { value ->
                if (value[x] == '1') oneCounter++
                else zerCounter++
            }

            var lookingFor = if (oneCounter >= zerCounter) "1" else "0"
            list.removeAll { it[x].toString() != lookingFor }
        }

        return list[0]
    }

    private fun findLeastCommon(list: MutableList<String>): String {
        val length = list[0].length
        for (x in 0 until length) {
            var oneCounter = 0
            var zerCounter = 0

            if (list.size == 1) return list[0]

            list.forEach { value ->
                if (value[x] == '1') oneCounter++
                else zerCounter++
            }

            var lookingFor = if (zerCounter <= oneCounter) "0" else "1"
            list.removeAll { it[x].toString() != lookingFor }
        }

        return list[0]
    }

    override fun optimizedSolutionPart1(input: String): Any {
        return ""
    }

    override fun optimizedSolutionPart2(input: String): Any {
        var list = convertStringTo2dList(input)
        var oxygenCount = calculateRating(list, this::findMostCommonOptimized)
        var co2Count = calculateRating(list, this::findLeastCommonOptimized)

        println("oxygen count: $oxygenCount")
        println("co2 count: $co2Count")

        return oxygenCount * co2Count
    }

    private fun convertStringTo2dList(input: String): List<List<Int>> {
        return input
            .splitMultiline()
            .map { row -> row.split("").filter { it.isNotBlank() }.map { it.toInt() } }
    }

    private fun calculateRating(rows: List<List<Int>>, method: (List<List<Int>>, Int) -> Int): Int {
        var matches: List<List<Int>> = rows
        for (column: Int in rows.indices) {
            val bit = method(matches, column)
            //matches = matches.filterNot { row -> row[column] != bit }
            matches = matches.filter { row -> row[column] == bit }
            if (matches.size == 1) break
        }

        return matches.first().toBase10()
    }

    private fun findMostCommonOptimized(rows: List<List<Int>>, columnIndex: Int): Int {
        val meanValue = rows.sumOf { it[columnIndex] } / rows.count().toDouble()
        return if (meanValue >= 0.5) 1 else 0
    }

    private fun findLeastCommonOptimized(rows: List<List<Int>>, columnIndex: Int): Int {
        val meanValue = rows.sumOf { it[columnIndex] } / rows.count().toDouble()
        return if (meanValue >= 0.5) 0 else 1
    }
}