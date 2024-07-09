package com.roomdbexample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotesPojo(val id: Long, var noteTitle: String, var noteDescription:String): Parcelable
