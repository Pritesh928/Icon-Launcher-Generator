package com.firstapp.nixinicon.service


import java.awt.Desktop
import java.net.URI
import java.util.Locale

fun openInBrowser(uri : URI) {
    val osName by lazy(LazyThreadSafetyMode.NONE) {
        System.getProperty("os.name").lowercase(Locale.getDefault())
    }
    val desktop = Desktop.getDesktop()
    when {
        Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(
            uri
        )
        "win" in osName -> Runtime.getRuntime().exec("open $uri")
        "mac" in osName -> Runtime.getRuntime().exec("open $uri")
        "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $uri")
        else -> throw RuntimeException("can't open $uri")
    }
}