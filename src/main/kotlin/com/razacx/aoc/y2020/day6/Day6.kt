package com.razacx.aoc.y2020.day6

fun getSumOfUniqueQuestionsAnsweredYesToPerGroup(input: String): Int {
    val uniqueQuestionsPerGroup = getGroups(input).map { it.replace("\n", "").toCharArray().toSet() }
    return uniqueQuestionsPerGroup.sumBy { it.size }
}

fun getSumOfQuestionsEveryoneAnsweredYesToPerGroup(input: String): Int {
    val questionsEveryoneAnsweredPerGroup = getGroups(input).map { group: String ->
        val answersPerIndividual = group.split("\n").map { it.toSet() }
        answersPerIndividual.reduce { answersIntersection, individualAnswers -> answersIntersection.intersect(individualAnswers) }
    }
    return questionsEveryoneAnsweredPerGroup.sumBy { it.size }
}

private fun getGroups(input: String) = input
    .replace("\r", "") // Because of Windows file encoding...
    .split("\n\n")

