package com.practice.hiltapplicationSample.presentations.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.hiltapplicationSample.presentations.viewmodels.AvatarListScreenViewModel

@Composable
fun AvatarListScreen(
    viewModel: AvatarListScreenViewModel = hiltViewModel(),
    backToCreation: () -> Unit
) {
    val avatarList = remember { viewModel.avatarListState }
    val isLoading by remember { viewModel.isLoading }

    LaunchedEffect(viewModel) {
        viewModel.fetchList()
    }
    
    Scaffold {
        LazyColumn {
            items(avatarList) { item ->
                Text(item.name)
            }
        }
    }
}
