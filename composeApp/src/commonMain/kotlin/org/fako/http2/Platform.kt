package org.fako.http2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform