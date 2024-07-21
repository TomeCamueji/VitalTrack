package com.example.vitaltrack.model

import androidx.room.Insert
import androidx.room.Query

interface Dao {
    @Insert
    fun insert(calc: Calc)

    @Query(" SELECT * FROM Calc WHERE type = :type")
    fun getRegisterByType(type:String):List<Calc>

}