package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myadventofcode2021.classes.Day05Line
import com.example.myadventofcode2021.classes.Day05LineSegment
import com.example.myadventofcode2021.helpers.Day05LineHelper
import com.example.myadventofcode2021.helpers.Day05LineSegmentHelper
import com.example.myadventofcode2021.toddginsberg.Day05Solution
import kotlin.math.pow
import kotlin.math.sqrt

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day05.solve()
}

object Day05: Solution() {
    private const val size = 10

    override fun myOwnSolutionPart1(input: String): Any {
        val listOfInputs = input.splitMultiline()
        var byteMap = Array(size * size) { 0 }

        listOfInputs.forEach {
            val ls = Day05LineSegment(it)
            Day05LineSegmentHelper.map(size, ls, byteMap, false)
        }

        return byteMap.count { it > 1 }
    }

    override fun myOwnSolutionPart2(input: String): Any {
        val listOfInputs = input.splitMultiline()
        var byteMap = Array(size * size) { 0 }

        listOfInputs.forEach {
            val ls = Day05LineSegment(it)
            Day05LineSegmentHelper.map(size, ls, byteMap, true)
        }

        return byteMap.count { it > 1 }
    }

    override fun optimizedSolutionPart1(input: String): Any {
        return Day05Solution(input.splitMultiline()).solvePart1()
//        return Day05LineHelper
//            .countOverlappingPoints(
//                input.splitMultiline(),
//                false
//            )
    }

    override fun optimizedSolutionPart2(input: String): Any {
        return Day05Solution(input.splitMultiline()).solvePart2()
//        return Day05LineHelper
//            .countOverlappingPoints(
//                input.splitMultiline(),
//                true
//            )
    }

    data class Coordinates(
        val x: Int,
        val y: Int
    ) {
        companion object {
            fun fromCommaSeparated(input: String): Coordinates {
                val (x: String, y: String) = input.split(",")
                return Coordinates(x.toInt(), y.toInt())
            }
        }
    }

    data class Line(
        val from: Coordinates,
        val to: Coordinates
    ) {
        private val euclideanDistance: Double = euclideanDistance()

        private fun euclideanDistance(): Double {
            return sqrt((to.x - from.x).toDouble().pow(2) + (to.y - from.y).toDouble().pow(2))
        }
    }
}