package com.firstapp.nixinicon

import java.io.File
import javax.swing.JFileChooser


val chooser = JFileChooser()
val result = chooser.showOpenDialog(null)

fun pickImage() : File? {
    if( result == JFileChooser.APPROVE_OPTION){
        return chooser.selectedFile
    }else{
        return null
    }
}