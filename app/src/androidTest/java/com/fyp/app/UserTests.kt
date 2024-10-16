package com.fyp.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.fyp.app.ui.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class UserTests {
    @get:Rule
    val rule = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginSuccessful() {
//        runBlocking {
//            rule.setContent {
//                DestinationsNavHost(navGraph = NavGraphs.root)
//            }
//
//            // Actions
//            rule.onNodeWithTag("login").performClick()
//            rule.onNodeWithText("Nombre de usuario").performTextInput("admin")
//            rule.onNodeWithText("Contraseña").performTextInput("admin")
//            rule.onNodeWithText("Iniciar sesión").performClick()
//        }
//
//        while (true) {
//            try {
//                rule.onNodeWithText("Enciclopedia de Plantas").isDisplayed()
//                break
//            } catch (e: Exception) {
//                continue
//            }
//
//        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginWithInvalidUsername() {
//        runBlocking {
//            rule.setContent {
//                DestinationsNavHost(navGraph = NavGraphs.root)
//            }
//            // Actions
//            rule.waitForIdle()
//            rule.onNodeWithTag("login").performClick()
//            rule.onNodeWithText("Nombre de usuario").performTextInput("invalidUser")
//            rule.onNodeWithText("Contraseña").performTextInput("admin")
//            rule.onNodeWithText("Iniciar sesión").performClick()
//
//            rule.waitUntilExactlyOneExists(
//                timeoutMillis = 10000,
//                matcher = hasText("Nombre de usuario inválido")
//            )
//        }
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
//        runBlocking {
//            rule.setContent {
//                DestinationsNavHost(navGraph = NavGraphs.root)
//            }
//
//            // Actions
//            rule.onNodeWithTag("login").performClick()
//            rule.onNodeWithText("Nombre de usuario").performTextInput("unverifiedUser")
//            rule.onNodeWithText("Contraseña").performTextInput("admin")
//            rule.onNodeWithText("Iniciar sesión").performClick()
//        }
//
//        rule.waitForIdle()
//
//        rule.waitUntil(timeoutMillis = 10000) {
//            try {
//                rule.onNodeWithText("Su cuenta no ha realizado la validación por correo.").isDisplayed()
//                true
//            } catch (e: Exception) {
//                false
//            }
//        }
//
//        rule.onNodeWithText("Su cuenta no ha realizado la validación por correo.").assertIsDisplayed()
    }

    @Test
    fun loginGoogle() {
        runBlocking() { // Invalid Inputs
            login("admin", "admin")
            rule.waitForIdle()
            rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
            // Actions
            // Checking
            rule.onNodeWithTag("profile_pic").performClick()
            rule.onNode(hasText("Mi Perfil")).performClick()
        }
    }

    @Test
    fun logout() {
        runBlocking() { // Invalid Inputs
            login("admin", "admin")
            rule.waitForIdle()
            rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
            // Actions
            rule.onNodeWithTag("profile_pic").performClick()
            rule.onNode(hasText("Cerrar Sesión")).performClick()

            // Checking
            rule.waitForIdle()
            rule.onNodeWithText("Nombre de usuario").assertExists()

        }
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
        runBlocking() { // Invalid Inputs
            login("admin", "admin")
//            rule.waitForIdle()
//            rule.setContent { DestinationsNavHost(navGraph = NavGraphs.root) }
//
//            // Actions
//            rule.onNodeWithTag("profile_pic").performClick()
//            rule.onNode(hasText("Mi Perfil")).performClick()
//            rule.onNode(hasText("Editar Usuario")).performClick()
//            rule.onNodeWithTag("lazy_columnn").performTouchInput { swipeUp() }
//            rule.onNodeWithText("Guardar").performClick()

            // Checking
//            rule.onNodeWithText("username").assertContentDescriptionEquals("admin")
        }
    }

// LOS TESTS DE VERIFICAR CONTRASEÑA Y CAMBIAR CONTRASEÑA NO SE HARÁN

}