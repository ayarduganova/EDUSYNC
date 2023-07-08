package com.project.edusync

import android.text.Editable
import java.lang.reflect.Constructor

class Subject(
    val day: String,
    val name: String,
    val auditory: String,
    val start: String,
    val end: String
    )
{
    constructor():this("", "", "", "","")
}

