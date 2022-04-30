package com.aqsin.cryptotracker.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_filter",
        indices = [Index(value = ["name"],
        unique = true)])
data class CryptoFilterEntity (
    @PrimaryKey(autoGenerate = true) var id : Long = 0,
    val name : String,
    var minValue : Float = 0f,
    var maxValue : Float = 0f
)