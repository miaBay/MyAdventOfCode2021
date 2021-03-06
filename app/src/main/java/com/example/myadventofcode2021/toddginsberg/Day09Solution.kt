package com.example.myadventofcode2021.toddginsberg

class Day09Solution(input: List<String>) {

    private val caves: Array<IntArray> = parseInput(input)

    fun solvePart1(): Int =
        caves.findLowPoints().sumOf {
            caves[it] +1
        }

    fun solvePart2(): Int =
        caves.findLowPoints()
            .map { getBasinSize(it) }
            .sortedDescending()
            .take(3)
            .reduce { a, b ->
                a * b
            }

    private fun getBasinSize(point: Point2d): Int {
        val visited = mutableSetOf(point)
        val queue = mutableListOf(point)

        while (queue.isNotEmpty()) {
            val newNeighbors = queue.removeFirst()
                .validNeighbors()
                .filter { it !in visited }
                .filter { caves[it] != 9 }

            visited.addAll(newNeighbors)
            queue.addAll(newNeighbors)
        }

        return visited.size
    }

    private fun Point2d.validNeighbors(): List<Point2d> =
        neighbors().filter { it in caves }

    private fun Array<IntArray>.findLowPoints(): List<Point2d> =
        //update this later, to test your idea
        flatMapIndexed { y, row ->
            row.mapIndexed { x, height ->
                Point2d(x, y).takeIf { point ->
                    point.validNeighbors().map {
                        caves[it]
                    }.all {
                        height < it
                    }
                }
            }.filterNotNull()
        }

    private fun parseInput(input: List<String>): Array<IntArray>  =
        input.map { row ->
            row.map { it.digitToInt() }.toIntArray()
        }.toTypedArray()

    private operator fun Array<IntArray>.get(point: Point2d): Int =
        this[point.y][point.x]

    private operator fun Array<IntArray>.contains(point: Point2d): Boolean =
        point.y in this.indices && point.x in this[point.y].indices
}