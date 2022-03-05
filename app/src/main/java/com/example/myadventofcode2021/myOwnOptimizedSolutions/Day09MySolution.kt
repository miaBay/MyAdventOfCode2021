package com.example.myadventofcode2021.myOwnOptimizedSolutions

class Day09MySolution() {
    fun solvePart1(locationPoints: List<List<Int>>): Int {
        var sumOfHeights = 0

        locationPoints.forEachIndexed { rIdx, row ->
            row.forEachIndexed { cIdx, current ->
                val up = if (rIdx > 0) locationPoints[rIdx -1][cIdx] else 0
                val prev = if (cIdx > 0) row[cIdx -1] else 0
                val next = if (cIdx < row.size -1) row[cIdx +1] else 0
                val down = if (rIdx < locationPoints.size -1) locationPoints[rIdx +1][cIdx] else 0

                sumOfHeights += when {
                    //first row
                    rIdx == 0 &&
                            (
                                    (cIdx == 0 && current < next && current < down) ||
                                            (current < prev && current < next && current < down) ||
                                            (cIdx == row.size -1 && current < prev && current < down)
                                    ) -> (current +1)
                    //in between
                    current < up && current < down &&
                            (
                                    (cIdx == 0 && current < next) ||
                                            (current < prev && current < next) ||
                                            (cIdx == row.size -1 && current < prev)
                                    ) -> (current +1)
                    //last row
                    rIdx == locationPoints.size -1 &&
                            (
                                    (cIdx == 0 && current < up && current < next) ||
                                            (current < prev && current < up && current < next) ||
                                            (cIdx == row.size -1 && current < prev && current < up)
                                    ) -> (current +1)
                    else -> 0
                }
            }
        }

        return sumOfHeights
    }

    private val HEIGHT = 5
    private val WIDTH = 10

    fun solvePart2(input: List<String>): Int {
        val map = Array(HEIGHT * WIDTH) { 0 }

        var i = 0
        input.forEach { row ->
            row.split("").filter { it.isNotEmpty() }.forEach { num ->
                map[i++] = num.toInt()
            }
        }

        val sizes = mutableListOf<Int>()
        for (y in 0 until HEIGHT) {
            for (x in 0 until WIDTH) {
                val value = map[(y * WIDTH) + x]
                var north = 10
                var south = 10
                var east = 10
                var west = 10

                if (y > 0) north = map[(y -1) * WIDTH +x]
                if (y < HEIGHT -1) south = map[(y +1) * WIDTH +x]
                if (x > 0) west = map[y * WIDTH + x -1]
                if (x < WIDTH -1) east = map[y * WIDTH + x +1]

                if (
                    value < north && value < south &&
                    value < west && value < east
                ) {
                    var visited = Array(WIDTH * HEIGHT) { false }
                    sizes.add(getSize(map, visited, x, y))
                }
            }
        }

        return sizes.sortedDescending().take(3).reduce { a, b -> a * b }
    }

    private fun getSize(map: Array<Int>, visited: Array<Boolean>, x: Int, y: Int): Int {
        val currentCount = map[y * WIDTH + x]
        visited[y * WIDTH + x] = true
        if (currentCount == 9) return 0

        var count = 1

        if (y > 0 && !visited[(y -1) * WIDTH +x])
            count += getSize(map, visited, x, y -1)

        if (y < HEIGHT -1 && !visited[(y +1) * WIDTH +x])
            count += getSize(map, visited, x, y +1)

        if (x > 0 && !visited[y * WIDTH + x -1])
            count += getSize(map, visited, x -1, y)

        if (x < WIDTH -1 && !visited[y * WIDTH + x +1])
            count += getSize(map, visited, x +1, y)

        return count
    }
}