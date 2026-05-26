package com.firstapp.nixinicon

import java.io.File
import java.nio.file.Files

fun extractZip(selectedZip : File) : File?{
    var tempDir = Files.createTempDirectory("tmpDir").toFile()
    return tempDir
}