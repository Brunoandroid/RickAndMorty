package com.example.rickandmorty.data.exception

import android.content.res.Resources
import com.example.rickandmorty.R
import java.lang.Exception

class FalhaAoBuscarPersonagensException :
    Exception(Resources.getSystem().getString(R.string.FalhaAoBuscarPersonagensException))
