package com.example.rickandmorty.data.exception

import android.content.res.Resources
import com.example.rickandmorty.R

class FalhaNaComunicacaoComApiException :
        Exception(Resources.getSystem().getString(R.string.FalhaNaComunicacaoComApi))
