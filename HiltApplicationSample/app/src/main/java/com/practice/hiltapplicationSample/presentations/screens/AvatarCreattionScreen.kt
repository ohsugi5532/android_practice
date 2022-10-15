package com.practice.hiltapplicationSample.presentations.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.hiltapplicationSample.presentations.viewmodels.AvatarCreationScreenViewModel

@Composable
fun AvatarCreationScreen(
    viewModel: AvatarCreationScreenViewModel = hiltViewModel(),
    navigateToList: () -> Unit
) {
    var text by remember { mutableStateOf<String>("") }

    Scaffold {
        Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
            OutlinedTextField(
                value = text,
                placeholder = {
                    Text(text = "Input name")
                },
                onValueChange = {
                    text = it
                },
                modifier = Modifier.padding(20.dp)
            )
            Button(
                content = {
                    Text(text = "Generate")
                },
                onClick = {
                    viewModel.createAvatar(text)
                }
            )
            Button(
                content = {
                    Text(text = "Save")
                },
                onClick = {
                    viewModel.saveAvatar()
                }
            )
            TextButton(
                content = {
                    Text(text = "Go to Avatar list")
                },
                onClick = {
                    navigateToList()
                }
            )
        }
    }
}
