package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day02.solve()
}

object Day02: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        var list = input.splitMultiline().map { it.split(" ") }.map { Pair(it[0], it[1].toInt()) }
        var forward = 0
        var depth = 0

        for (value in list) {
            when (value.first) {
                "forward" -> forward += value.second
                "up" -> depth -= value.second
                "down" -> depth += value.second
            }
        }

        return forward * depth
    }

    override fun myOwnSolutionPart2(input: String): Any {
        var list = input.splitMultiline().map { it.split(" ") }.map { Pair(it[0], it[1].toInt()) }
        var forward = 0
        var depth = 0
        var aim = 0

        for (value in list) {
            when (value.first) {
                "up" -> aim -= value.second
                "down" -> aim += value.second
                "forward" -> {
                    forward += value.second
                    depth += aim * value.second
                }
            }
        }

        return forward * depth
    }

    override fun optimizedSolutionPart1(input: String): Any {
        return ""
    }

    override fun optimizedSolutionPart2(input: String): Any {
        return ""
    }
}