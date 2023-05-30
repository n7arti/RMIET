package com.n7art.rmiet.Model

class Day(_lesson1: Lesson, _lesson2: Lesson, _lesson3: Lesson, _lesson4: Lesson, _lesson5: Lesson, _lesson6: Lesson, _lesson7: Lesson) {
    var lesson1: Lesson
    var lesson2: Lesson
    var lesson3: Lesson
    var lesson4: Lesson
    var lesson5: Lesson
    var lesson6: Lesson
    var lesson7: Lesson

    init {
        lesson1 = _lesson1
        lesson2 = _lesson2
        lesson3 = _lesson3
        lesson4 = _lesson4
        lesson5 = _lesson5
        lesson6 = _lesson6
        lesson7 = _lesson7
    }

    fun LessonToList(): List<Lesson> {
        return listOf(lesson1, lesson2, lesson3, lesson4, lesson5, lesson6, lesson7)
    }
}