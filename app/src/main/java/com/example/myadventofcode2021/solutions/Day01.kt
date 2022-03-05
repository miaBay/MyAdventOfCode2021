package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    println("day01 solution")
    Day01.solve()
}

object Day01: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        val inputList = input.splitMultiline().map { it.toInt() }
        var incrementedDepth = 0
        var previousDepth = 0

        inputList.forEachIndexed { index, value ->
            if (index > 0 && value > previousDepth) incrementedDepth++
            previousDepth = value
        }

        return incrementedDepth
    }

    override fun myOwnSolutionPart2(input: String): Any {
        val inputList = input.splitMultiline().map { it.toInt() }
        var incrementedDepth = 0
        var previousDepth = 0

        for (i in 0..inputList.size - 3) {
            val current = (inputList[i] + inputList[i+1] + inputList[i+2])
            if (i > 0 && current > previousDepth) incrementedDepth++

            previousDepth = current
        }

        return incrementedDepth
    }

    override fun optimizedSolutionPart1(input: String): Any {
        val list = input.splitMultiline().map { it.toInt() }
        return optimizedSolution(list)
    }

    override fun optimizedSolutionPart2(input: String): Any {
        val list = input.splitMultiline().map { it.toInt() }
        val windowedList = list.windowed(3).map { it.sum() }
        return optimizedSolution(windowedList)
    }

    private fun optimizedSolution(values: List<Int>): Int {
        var counter = 0
        var previousValue = Int.MAX_VALUE

        for (value in values) {
            if (value > previousValue) counter++
            previousValue = value
        }

        return counter
    }
}