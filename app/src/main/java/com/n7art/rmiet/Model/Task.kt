package com.n7art.rmiet.Model

import android.os.Parcel
import android.os.Parcelable
import java.util.Calendar

class Task(_name: String, _text: String, _subjects: String, _date: Calendar): Parcelable {
    var name: String
    var text: String
    var subject: String
    var date: Calendar

    init {
        name = _name
        text = _text
        subject = _subjects
        date = _date
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readSerializable() as Calendar
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(text)
        parcel.writeString(subject)
        parcel.writeSerializable(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}

