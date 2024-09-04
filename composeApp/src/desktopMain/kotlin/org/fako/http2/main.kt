package org.fako.http2

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import networking.InsultCensorClient

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Http2",
    ) {
        App(client = remember{
            InsultCensorClient(HttpClient(CIO))
    } )
}
}