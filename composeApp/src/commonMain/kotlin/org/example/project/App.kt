package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import org.koin.core.context.startKoin
import org.koin.dsl.module
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

import org.example.project.data.MongoDB
import org.example.project.presentation.screen.home.HomeScreen
import org.example.project.presentation.screen.home.HomeViewModel
import org.example.project.presentation.screen.task.TaskViewModel

import todokmp.composeapp.generated.resources.Res
import todokmp.composeapp.generated.resources.compose_multiplatform

val lightRedColor = Color(color = 0xFFF57D88)
val darkRedColor = Color(color = 0xFF77000B)

@Composable
@Preview
fun App() {
    initializeKoin()

    val lightColors = lightColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor
    )
    val darkColors = darkColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor
    )
    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) darkColors else lightColors
    )

    MaterialTheme(colorScheme = colors) {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}

val mongoModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
}

fun initializeKoin() {
    startKoin {
        modules(mongoModule)
    }
}
