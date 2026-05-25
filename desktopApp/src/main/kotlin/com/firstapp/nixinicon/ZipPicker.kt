package com.firstapp.nixinicon


import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

var fileChooser = JFileChooser()
var zipFilter = FileNameExtensionFilter("ZIP" , "zip")


fun PickZip() : File? {
    fileChooser.fileFilter = zipFilter
    val result = fileChooser.showOpenDialog(null)
    if(result == JFileChooser.APPROVE_OPTION){
        val selectedFile = fileChooser.selectedFile
        return selectedFile
    }else{
        return null
    }
}
