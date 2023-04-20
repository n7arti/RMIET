package com.n7art.rmiet.Model

class Schedule(_ch1: Week, _z1: Week, _ch2: Week, _z2: Week) {
    var ch1: Week
    var z1: Week
    var ch2: Week
    var z2: Week

    init {
        this.ch1 = _ch1
        this.z1 = _z1
        this.ch2 = _ch2
        this.z2 = _z2
    }
    fun WeekToList(): List<Week> {
        return listOf(ch1,z1,ch2,z2)
    }
}