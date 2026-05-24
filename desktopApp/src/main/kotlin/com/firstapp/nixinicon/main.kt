package com.firstapp.nixinicon

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.firstapp.nixinicon.MainScreen




fun main() = application(){
    Window(
        onCloseRequest = ::exitApplication,
        title = "Nixin Icon"
    ){
      MainScreen()
    }
}