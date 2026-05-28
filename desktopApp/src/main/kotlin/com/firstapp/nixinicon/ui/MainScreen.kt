package com.firstapp.nixinicon.ui


import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.io.File
import javax.swing.JFileChooser
import com.firstapp.nixinicon.service.PickZip
import com.firstapp.nixinicon.service.extractZip


@Composable
@Preview
fun MainScreen() {
    var selectedZip by remember { mutableStateOf<File?>(null) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
       {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {
                selectedZip = PickZip()
                if(selectedZip != null){
                    val extracted = extractZip(selectedZip!!)
                    print(extracted?.absolutePath)
                }

            }){
                Text("Upload ZIP")
            }
            Text(selectedZip?.name ?: "No Zip Selected")
        }
    }
}
