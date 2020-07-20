package com.example.swenson.currencies.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CurrencyEntity(val initials:String, val value: Double) : Parcelable