package com.safetorun.logger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform