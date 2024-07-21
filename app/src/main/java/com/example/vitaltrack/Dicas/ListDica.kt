package com.example.vitaltrack.Dicas

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ListDica(
    val id:Int,
  @StringRes  val string: Int,
    @StringRes val string1:Int,
   @DrawableRes val drawable:Int
)
