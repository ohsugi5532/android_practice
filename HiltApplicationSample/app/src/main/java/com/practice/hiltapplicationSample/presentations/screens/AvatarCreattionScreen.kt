package com.practice.hiltapplicationSample.presentations.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.practice.hiltapplicationSample.presentations.viewmodels.AvatarCreationScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AvatarCreationScreen(
    viewModel: AvatarCreationScreenViewModel = hiltViewModel(),
    navigateToList: () -> Unit
) {
    var name by remember { mutableStateOf<String>("") }
    var url by remember { mutableStateOf<String>("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Create")
                    },
                )
            },
            content = {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = name,
                        placeholder = {
                            Text(text = "Input name")
                        },
                        onValueChange = {
                            name = it
                        },
                        modifier = Modifier.padding(20.dp)
                    )
                    Row {
                        Button(
                            content = {
                                Text(text = "Generate")
                            },
                            onClick = {
                                url = viewModel.generateAvatarUrl(name)
                                keyboardController?.hide()
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            enabled = url.isNotEmpty(),
                            content = {
                                Text(text = "Save")
                            },
                            onClick = {
                                viewModel.saveAvatar(
                                    name = name,
                                    url = url,
                                )
                            }
                        )
                    }
                    TextButton(
                        content = {
                            Text(text = "Go to Avatar list")
                        },
                        onClick = {
                            navigateToList()
                        }
                    )
                    if (url.isNotEmpty()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(url)
                                .decoderFactory(SvgDecoder.Factory())
                                .crossfade(true)
                                .build(),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier.clip(CircleShape)
                        )
                    }
                }
            }
        )
    }
}
