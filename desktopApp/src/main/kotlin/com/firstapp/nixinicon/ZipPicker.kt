package com.firstapp.nixinicon


import java.io.FilenameFilter
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

var fileChooser = JFileChooser()
var zipFilter = FileNameExtensionFilter("ZIP" , "zip")


fun PickZip() {
    val selectedFile = fileChooser.selectedFile
    fileChooser.fileFilter = zipFilter
    val result = fileChooser.showOpenDialog(null)
    print("selected file : " + {selectedFile.absolutePath} )
}
