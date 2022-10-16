package com.practice.hiltapplicationSample.presentations.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.practice.hiltapplicationSample.presentations.viewmodels.AvatarListScreenViewModel

@Composable
fun AvatarListScreen(
    viewModel: AvatarListScreenViewModel = hiltViewModel(),
    backToCreation: () -> Unit
) {
    LaunchedEffect(viewModel) {
        viewModel.fetchList()
    }

    Surface {
        Scaffold(
            modifier = Modifier.background(Color.Cyan),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "List")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            backToCreation()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "go back"
                            )
                        }
                    },
                )
            },
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = viewModel.avatars, itemContent =  { item ->
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(item.url)
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(64.dp)
                                    .clip(CircleShape)
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = item.name,
                                fontSize = 16.sp,
                            )
                        }
                    })
                }
            }
        )
    }

}
