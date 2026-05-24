package com.firstapp.nixinicon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform