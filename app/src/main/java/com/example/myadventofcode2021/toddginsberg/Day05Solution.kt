package com.example.myadventofcode2021.toddginsberg

class Day05Solution(input: List<String>) {

    private val instructions = input.map { parseRow(it) }

    fun solvePart1(): Int =
        solve { it.first sharesAxisWith it.second }

    fun solvePart2(): Int = solve { true }

    private fun solve(lineFilter: (Pair<Point2d, Point2d>) -> Boolean) =
        instructions
            .filter { lineFilter(it) }
            .flatMap { it.first lineTo it.second }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }

    private fun parseRow(input: String): Pair<Point2d, Point2d> =
        Pair(
            parsePoint(input) { strValue ->
                strValue.substringBefore(" ")
            },
            parsePoint(input) { strValue ->
                strValue.substringAfterLast(" ")
            }
//            input.substringBefore(" ").split(",").map { it.toInt() }.let { Point2d(it.first(), it.last()) },
//            input.substringAfter(" ").split(",").map { it.toInt() }.let { Point2d(it.first(), it.last()) }
        )

    private fun parsePoint(input: String, parseString: (String) -> String): Point2d =
        parseString(input)
            .split(",")
            .map { it.toInt() }.let {
                Point2d(it.first(), it.last())
            }
}