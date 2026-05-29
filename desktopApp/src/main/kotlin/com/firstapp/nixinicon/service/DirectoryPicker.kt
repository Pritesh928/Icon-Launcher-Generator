package com.firstapp.nixinicon.service

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.io.File
import javax.swing.JFileChooser


val pathchooser = JFileChooser()


fun pickDir() : File?{
    pathchooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    val result = pathchooser.showOpenDialog(null)
    if(result == JFileChooser.APPROVE_OPTION){
        val selecteDir = pathchooser.selectedFile
        return selecteDir
    }else {
        return null
    }
}