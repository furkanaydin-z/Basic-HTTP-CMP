package org.fako.http2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import http2.composeapp.generated.resources.Res
import http2.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch
import networking.InsultCensorClient
import networking.UserInfo
import util.NetworkError
import util.onError
import util.onSuccess

@Composable
@Preview
fun App(client: InsultCensorClient) {
    MaterialTheme {
       var userInfo by remember { mutableStateOf<UserInfo?>(null) }
        var userId by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<NetworkError?>(null) }
        val scope = rememberCoroutineScope()


        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)) {

            TextField(
                value = userId,
                onValueChange = {userId = it},
                placeholder = { Text("Get User Id") }
            )
            Button(onClick ={
                scope.launch {
                    isLoading = true
                    errorMessage = null

                    client.getUserInfo(userId.toIntOrNull() ?: 1)
                        .onSuccess {
                            userInfo = it
                        }.onError {
                            errorMessage = it
                        }
                        isLoading = false
                }
            } ){
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(15.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                } else {
                    Text("Get User Info")
                }
            }
            userInfo?.let {
                Text("Name ${it.name}")
            }
            errorMessage?.let {
                Text(
                    text = it.name,
                    color = Color.Red
                )
            }

            }
        }
    }
