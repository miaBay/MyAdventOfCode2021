package com.example.myadventofcode2021.helpers

import com.example.myadventofcode2021.classes.Day05Line

object Day05LineHelper {
    private val numberRegex = "([0-9]+)".toRegex()

    fun countOverlappingPoints(list: List<String>, includeDiagonals: Boolean): Int {
        return list.mapNotNull { line ->
            val matches = numberRegex
                .findAll(line)
                .map(MatchResult::value)
                .map(String::toInt)
                .toList()

            val x1 = matches[0]
            val y1 = matches[1]
            val x2 = matches[2]
            val y2 = matches[3]

            if (includeDiagonals) Day05Line(x1, y1, x2, y2)
            else {
                if (x1 != x2 && y1 != y2) null
                else {
                    Day05Line(x1, y1, x2, y2)
                }
            }
        }
            .flatMap(Day05Line::points)
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .count()
    }
}