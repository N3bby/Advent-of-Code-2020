package com.razacx.aoc.y2020.day6

fun getSumOfUniqueQuestionsAnsweredYesToPerGroup(input: String): Int {
    val uniqueQuestionsAnsweredYesToPerGroup = getGroups(input).map { it.replace("\n", "").toCharArray().toSet() }
    return uniqueQuestionsAnsweredYesToPerGroup.sumBy { it.size }
}

fun getSumOfQuestionsEveryoneAnsweredYesToPerGroup(input: String): Int {
    val questionsEveryoneAnsweredYesToPerGroup = getGroups(input).map { group: String ->
        val answeredYesToPerIndividual = group.split("\n").map { it.toSet() }
        answeredYesToPerIndividual.reduce { answersIntersection, individualYesAnswers -> answersIntersection.intersect(individualYesAnswers) }
    }
    return questionsEveryoneAnsweredYesToPerGroup.sumBy { it.size }
}

private fun getGroups(input: String) = input
    .replace("\r", "") // Because of Windows file encoding...
    .split("\n\n")

