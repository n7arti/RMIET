package com.n7art.rmiet.Model

class Week(_mon: Day, _tue: Day, _wed: Day, _thu: Day, _fri: Day, _sat: Day) {
    var mon: Day
    var tue: Day
    var wed: Day
    var thu: Day
    var fri: Day
    var sat: Day

    init {
        mon = _mon
        tue = _tue
        wed = _wed
        thu = _thu
        fri = _fri
        sat = _sat
    }

    fun DayToList(): List<Day> {
        return listOf(mon, tue, wed, thu, fri, sat)
    }
}