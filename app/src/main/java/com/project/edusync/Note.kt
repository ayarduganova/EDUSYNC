package com.project.edusync

import android.text.Editable
import android.widget.EditText

data class Note(
    val id: String,
    var name: String,
    var description: String,
){
    constructor() : this("", "", "")
}
