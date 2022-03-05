package com.example.myadventofcode2021.toddginsberg

import kotlin.math.absoluteValue

class Day07(input: String) {
    private val crabs: Map<Int, Int> = input
        .split(",")
        .map(String::toInt)
        .groupingBy { it }
        .eachCount()

    fun solvePart1(): Int = solve { it }

    fun solvePart2(): Int = solve { distance ->
        ((distance *(distance +1)) /2)
    }

    private fun solve(fuelCost: (Int) -> Int): Int =
        crabs.keys.asRange().minOf { target ->
            crabs.map { (crab, crabCount) ->
                fuelCost((target - crab).absoluteValue) * crabCount
            }.sum()
        }

    private fun Set<Int>.asRange(): IntRange = this.minOf { it }..this.maxOf { it }
}