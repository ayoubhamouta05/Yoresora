package com.youppix.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform