package com.firstapp.nixinicon


import javax.swing.JFileChooser

var chooser = JFileChooser()


fun PickZip() {
    chooser.showOpenDialog(null)
}
