package com.firstapp.nixinicon.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstapp.nixinicon.service.*
import java.io.File
import java.net.URI

@Composable
@Preview
fun MainScreen() {
    var selectedZip by remember { mutableStateOf<File?>(null) }
    var selectedDir by remember { mutableStateOf<File?>(null) }
    var isDone by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Nixin Icon",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.3).sp
        )
        Text(
            text = "Install Android launcher icons into your project in three steps",
            fontSize = 13.sp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.55f)
        )

        Spacer(modifier = Modifier.height(4.dp))
        StepCard {
            StepHeader(number = "1", title = "Generate")
            Text(
                text = "Create your launcher icon using Android Asset Studio — supports clip art, images, and text.",
                fontSize = 13.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.55f),
                modifier = Modifier.padding(start = 32.dp, bottom = 12.dp)
            )
            Row(modifier = Modifier.padding(start = 32.dp)) {
                OutlinedButton(onClick = {
                    openInBrowser(URI("https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html"))
                }) {
                    Icon(Icons.Outlined.OpenInBrowser, contentDescription = null,
                        modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Open Asset Studio", fontSize = 13.sp)
                }
            }
        }
        StepCard {
            StepHeader(number = "2", title = "Upload")
            Text(
                text = "Upload the ZIP exported from Android Asset Studio.",
                fontSize = 13.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.55f),
                modifier = Modifier.padding(start = 32.dp, bottom = 12.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(start = 32.dp)
            ) {
                OutlinedButton(onClick = {
                    selectedZip = PickZip()
                    if (selectedZip != null) {
                        extractZip(selectedZip!!)
                    }
                }) {
                    Icon(Icons.Outlined.Upload, contentDescription = null,
                        modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Choose ZIP", fontSize = 13.sp)
                }
                Text(
                    text = selectedZip?.name ?: "No file selected",
                    fontSize = 12.sp,
                    color = if (selectedZip != null)
                        MaterialTheme.colors.onSurface
                    else
                        MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
                )
            }

            Divider(modifier = Modifier.padding(start = 32.dp, top = 16.dp, bottom = 14.dp))
            StepHeader(number = "3", title = "Locate")
            Text(
                text = "Select the res/ folder inside your Android project.",
                fontSize = 13.sp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.55f),
                modifier = Modifier.padding(start = 32.dp, bottom = 12.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(start = 32.dp)
            ) {
                OutlinedButton(onClick = {
                    selectedDir = pickDir()
                }) {
                    Icon(Icons.Outlined.Folder, contentDescription = null,
                        modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Select res/ folder", fontSize = 13.sp)
                }
                Text(
                    text = selectedDir?.name ?: "No folder selected",
                    fontSize = 12.sp,
                    color = if (selectedDir != null)
                        MaterialTheme.colors.onSurface
                    else
                        MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
                )
            }
        }
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Install icons", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Text(
                        "Copy mipmap folders into your project's res directory",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                    )
                }
                Button(
                    onClick = {
                        installMipmap()
                        isDone = true
                    }
                ) {
                    Icon(Icons.Outlined.PlayArrow, contentDescription = null,
                        modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(if (isDone) "Done" else "Start", fontSize = 13.sp)
                }
            }
        }

        if (isDone) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Icon(Icons.Outlined.CheckCircle, contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colors.primary)
                Text(
                    "Done — mipmap folders installed successfully",
                    fontSize = 13.sp,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
private fun StepCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        border = ButtonDefaults.outlinedBorder,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp), content = content)
    }
}

@Composable
private fun StepHeader(number: String, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f),
            modifier = Modifier.size(22.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(number, fontSize = 11.sp, fontWeight = FontWeight.Medium)
            }
        }
        Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}