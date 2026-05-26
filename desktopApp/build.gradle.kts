import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.shared)

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
    application {
        mainClass = "com.firstapp.nixinicon.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Nixin Icon"
            packageVersion = "1.0.0"
            windows {
                //Locating app in the PC/Laptop instead of finding in directories :)
                shortcut = true
                menu = true
                menuGroup = "Nixin Icon"
                // icon directory location redirect!!
                iconFile.set(project.file("${rootProject.rootDir}/shared/src/jvmMain/resources/icon.ico"))


            }
        }
    }
}