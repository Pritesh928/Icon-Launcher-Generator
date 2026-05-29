package com.firstapp.nixinicon.service

import javax.swing.JFileChooser


val chooser = JFileChooser()
fun locatedir() {
    val result = chooser.showOpenDialog(null)
}