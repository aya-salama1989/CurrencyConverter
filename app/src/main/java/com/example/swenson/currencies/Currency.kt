package com.example.swenson.currencies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Currency(val initials:String, val value: Double) : Parcelable