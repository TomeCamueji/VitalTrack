package com.example.vitaltrack.FARMAS

import androidx.annotation.StringRes

data class Listfarm(
    val Id: Int,
    @StringRes val name: Int,
    @StringRes val avaliacao: Int,
    @StringRes val endreco: Int,
    @StringRes val status: Int
)
