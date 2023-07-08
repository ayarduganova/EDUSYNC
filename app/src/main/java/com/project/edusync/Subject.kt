package com.project.edusync

import android.text.Editable
import java.lang.reflect.Constructor

class Subject(
    val id: String,
    val day: String,
    var name: String,
    var auditory: String,
    var start: String,
    var end: String
    )
{
    constructor():this("", "", "", "","", "")
}

