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
            STATUS_ALIVE -> R.color.status_alive
            STATUS_DEAD -> R.color.status_dead
            STATUS_UNKNOWN -> R.color.status_unknown
            else -> R.color.status_unknown
        }
    }
}