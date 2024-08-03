package com.fyp.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class UserTests {
    @get:Rule
    val rule = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginSuccessful() {
        runBlocking {
            rule.setContent {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }

            // Actions
            rule.onNodeWithTag("login").performClick()
            rule.onNodeWithText("Nombre de usuario").performTextInput("admin")
            rule.onNodeWithText("Contraseña").performTextInput("admin")
            rule.onNodeWithText("Iniciar sesión").performClick()
        }

        while (true) {
            try {
                rule.onNodeWithText("Enciclopedia de Plantas").isDisplayed()
                break
            } catch (e: Exception) {
                continue
            }

        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginWithInvalidUsername() {
        runBlocking {
            rule.setContent {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }

            // Actions
            rule.onNodeWithTag("login").performClick()
            rule.onNodeWithText("Nombre de usuario").performTextInput("invalidUser")
            rule.onNodeWithText("Contraseña").performTextInput("admin")
            rule.onNodeWithText("Iniciar sesión").performClick()

            rule.waitUntilExactlyOneExists(
                timeoutMillis = 10000,
                matcher = hasText("Nombre de usuario inválido")
            )
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginWithInvalidPassword() {
        runBlocking {
            rule.setContent {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }

            // Actions
            rule.onNodeWithTag("login").performClick()
            rule.onNodeWithText("Nombre de usuario").performTextInput("admin")
            rule.onNodeWithText("Contraseña").performTextInput("w")
            rule.onNodeWithText("Iniciar sesión").performClick()

            rule.waitForIdle()

            rule.waitUntilExactlyOneExists(timeoutMillis = 10000, matcher = hasText("Contraseña inválida"))
        }


    }

    @Test
    fun loginWithUnverifiedEmail() {
        runBlocking {
            rule.setContent {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }

            // Actions
            rule.onNodeWithTag("login").performClick()
            rule.onNodeWithText("Nombre de usuario").performTextInput("unverifiedUser")
            rule.onNodeWithText("Contraseña").performTextInput("admin")
            rule.onNodeWithText("Iniciar sesión").performClick()
        }

        rule.waitForIdle()

        rule.waitUntil(timeoutMillis = 10000) {
            try {
                rule.onNodeWithText("Su cuenta no ha realizado la validación por correo.").isDisplayed()
                true
            } catch (e: Exception) {
                false
            }
        }

        rule.onNodeWithText("Su cuenta no ha realizado la validación por correo.").assertIsDisplayed()
    }

    @Test
    fun loginGoogle() {
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun logout() {
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun register() {
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun registerGoogle() {
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

    @Test
    fun updateUser() {
        rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
        // TODO
        // Actions
        // Checking
    }

// LOS TESTS DE VERIFICAR CONTRASEÑA Y CAMBIAR CONTRASEÑA NO SE HARÁN

}