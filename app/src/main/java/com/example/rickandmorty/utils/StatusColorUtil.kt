package com.example.rickandmorty.utils

import androidx.annotation.ColorRes
import com.example.rickandmorty.R
import com.example.rickandmorty.utils.Constants.Companion.STATUS_ALIVE
import com.example.rickandmorty.utils.Constants.Companion.STATUS_DEAD
import com.example.rickandmorty.utils.Constants.Companion.STATUS_UNKNOWN

object StatusColorUtil {
    @ColorRes
    fun getColorForStatus(status: String): Int {
        return when (status.lowercase()) {
            STATUS_ALIVE -> R.color.greenPersonality
            STATUS_DEAD -> R.color.redPersonality
            STATUS_UNKNOWN -> R.color.darkGray
            else -> R.color.darkGray
        }
    }
}