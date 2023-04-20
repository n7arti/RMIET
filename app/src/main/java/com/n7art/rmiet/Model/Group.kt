package com.n7art.rmiet.Model

class Group(_name: String, _schedule: Schedule)  {
    var name: String
    var schedule: Schedule

    init{
        name = _name;
        schedule = _schedule;
    }
}