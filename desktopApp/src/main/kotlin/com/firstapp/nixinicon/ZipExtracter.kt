package com.firstapp.nixinicon

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.file.Files
import java.util.zip.ZipInputStream


fun extractZip(selectedZip : File) : File?{
    var tempDir = Files.createTempDirectory("tmp").toFile()
    ZipInputStream(
        FileInputStream(selectedZip)
    ).use { zipStream ->
        var entry = zipStream.nextEntry
        while(entry != null){
            var destination : File
            if(entry.isDirectory){
                destination = File(tempDir , entry.name)
                destination.mkdirs()
            }else {
                destination = File(tempDir , entry.name)
                destination.parentFile.mkdirs()
                destination.outputStream().use{
                    zipStream.copyTo(it)
                }
            }
            entry = zipStream.nextEntry
            
        }
    }
    return tempDir
}