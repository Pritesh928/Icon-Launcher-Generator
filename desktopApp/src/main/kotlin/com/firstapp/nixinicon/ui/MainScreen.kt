package com.firstapp.nixinicon.ui


import androidx.annotation.Size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import javax.swing.JFileChooser
import com.firstapp.nixinicon.service.PickZip
import com.firstapp.nixinicon.service.extractZip
import com.firstapp.nixinicon.service.locatedir
import java.net.URI
import com.firstapp.nixinicon.service.openInBrowser
import java.awt.Color

@Composable
@Preview
fun MainScreen() {
    var selectedZip by remember { mutableStateOf<File?>(null) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Generate", style = TextStyle(
                fontSize = 30.sp,
            ))
            Text("Generate Your own launcher icon using images clip art and more.", style = TextStyle(
                fontSize = 18.sp,
            ))
            Button(onClick = {
                openInBrowser(URI("https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html"))
                }){
                    Text("Generate Icon")
                }
            Text("Upload", style = TextStyle(
                fontSize = 30.sp,
            ))
            Text("Upload zip file that is generated from android asset studio", style = TextStyle(
                fontSize = 18.sp,
            ))
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
            Text("Locate", style = TextStyle(
                fontSize = 30.sp,
            ))
            Text("locate your project directory res folder where we can paste the icon folder's", style = TextStyle(
                fontSize = 18.sp,
            ))
            Button(onClick = {
                locatedir()
            }){
                Text("Locate Dir")
            }
            Text("---------------------------------------------------------------------------------------", style = TextStyle(
                fontSize = 18.sp,
            ))
            Button(onClick = {
                
            }){
                Text("Start >>>")
            }
        }
    }
}
