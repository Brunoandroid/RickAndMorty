package com.example.rickandmorty.data.model

import com.example.rickandmorty.data.model.login.Login
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginTest {

    @Test
    fun deve_VerificarEmail_AposInformarLogin() {
        val email = "null96@zmail.com"
        val senha = 1345678

        val loginRetorno = Login(email, senha)

        assertEquals(email, loginRetorno.email)

    }

    @Test
    fun deve_VerificarSenha_AposInformarLogin() {
        val email = "null96@zmail.com"
        val senha = 1345678

        val loginRetorno = Login(email, senha)

        assertThat(loginRetorno.senha).isEqualTo(senha)
    }
}