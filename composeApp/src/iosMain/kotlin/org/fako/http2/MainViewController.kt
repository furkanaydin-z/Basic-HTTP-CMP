package org.fako.http2

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import networking.InsultCensorClient

fun MainViewController() = ComposeUIViewController { App(
    client = remember{
        InsultCensorClient(HttpClient(CIO)) })

}