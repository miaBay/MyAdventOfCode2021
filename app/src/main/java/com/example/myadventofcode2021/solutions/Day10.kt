package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

object Day10: Solution() {
    private val mapChunk = mapOf(
        ")" to "(",
        "]" to "[",
        "}" to "{",
        ">" to "<"
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun myOwnSolutionPart1(input: String): Any {
        val mapIrregularChunks = mapOf(
            ")" to 3,
            "]" to 57,
            "}" to 1197,
            ">" to 25137
        )

        var solution = 0
        var sizeOfFoundIrregularities = mutableMapOf<String, Int>()
        val arrInput = parseInput(input)
        for (i in arrInput.indices) fl@{
            val row = arrInput[i]
            var dequeue = Stack<String>()
            for (innerIndex in row.indices) {
                val chunk = row[innerIndex].toString()
                when (mapChunk[chunk]) {
                    null -> dequeue.push(chunk)
                    dequeue.peek() -> dequeue.pop()
                    else -> {
                        val current = sizeOfFoundIrregularities.getOrDefault(chunk, 0)
                        sizeOfFoundIrregularities[chunk] = if (current == 0) 1 else current +1
                        break
                    }
                }
            }
        }

        mapIrregularChunks.forEach { (chunk, value) ->
            sizeOfFoundIrregularities.forEach sizeFe@{ (foundChunk, count) ->
                if (chunk == foundChunk) {
                    solution += (value * count)
                    return@sizeFe
                }
            }
        }

        return solution
    }

    //what is the required solution? mis score of the list...
    //todo: get all the missing chunks
    //todo: compute each value following the criteria
    //todo: get the mid score...
    @RequiresApi(Build.VERSION_CODES.N)
    override fun myOwnSolutionPart2(input: String): Any {
        val valueOfEachChunks = mapOf(
            ")" to 1,
            "]" to 2,
            "}" to 3,
            ">" to 4
        )

        val mapOfChunksInOrder = mapOf(
            "(" to ")",
            "[" to "]",
            "{" to "}",
            "<" to ">"
        )

        var missingChunks = mutableListOf<String>()
        val arrInput = parseInput(input)
        for (i in arrInput.indices) fl@{
            val row = arrInput[i]
            var dequeue = Stack<String>()
            for (innerIndex in row.indices) {
                val chunk = row[innerIndex].toString()
                when (mapChunk[chunk]) {
                    null -> dequeue.push(chunk)
                    dequeue.peek() -> dequeue.pop()
                    else -> {
                        if (innerIndex != row.length -1) {
                            dequeue.clear()
                            break
                        }
                    }
                }
            }

            var orderedChunks = ""
            while (dequeue.isNotEmpty()) {
                orderedChunks += mapOfChunksInOrder[dequeue.pop()]
            }

            if (orderedChunks.isNotBlankOrEmpty()) {
                missingChunks.add(orderedChunks)
            }
        }

        val allAnswers = mutableListOf<Long>()
        for (i in missingChunks.indices) {
            var answer: Long = 0
            missingChunks[i].map(Char::toString).forEach { missingChunk ->
                answer = (answer * 5) + valueOfEachChunks[missingChunk]!!.toLong()
            }
            allAnswers.add(answer)
        }

        val mean: Int = (allAnswers.size /2)
        allAnswers.sort()

        return allAnswers[mean]
    }

    override fun optimizedSolutionPart1(input: String): Any {
        return com.example.myadventofcode2021.toddginsberg.Day10(input.splitMultiline()).solvePart1()
    }

    override fun optimizedSolutionPart2(input: String): Any {
        return com.example.myadventofcode2021.toddginsberg.Day10(input.splitMultiline()).solvePart2()
    }

    private fun parseInput(input: String): Array<String> =
        input.splitMultiline().toTypedArray()
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day10.solve()
}