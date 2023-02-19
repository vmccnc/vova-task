package com.app.vova_task.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.app.vova_task.R
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.presentation.vm.HitViewModel
import com.skydoves.landscapist.glide.GlideImage


@ExperimentalFoundationApi
@Composable
fun HitScreen(
    machineId: Long?,
    vm: HitViewModel = hiltViewModel(),
    navBack: () -> Unit
) {
    val hit: Hit by vm.hit

    machineId?.let {
        vm.getMachineById(it)
    }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(hit.tags, Modifier.fillMaxWidth())
                },
                navigationIcon = {
                    IconButton(onClick = { navBack() }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                    }
                },

                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 1.dp,

                )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(start = 8.dp, end = 8.dp)) {
            item {


                Card(elevation = 4.dp) {
//                    Image(
//                        painter = rememberImagePainter(data = hit.largeImageURL) {
//                            this.error(R.drawable.ic_broken_image)
//                            this.placeholder(R.drawable.ic_placeholder)
//                        },
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(230.dp),
//                        contentScale = ContentScale.Crop
//                    )


                GlideImage(
                    imageModel = hit.largeImageURL,
                    contentScale = ContentScale.Crop,
//                    placeHolder =  R.drawable.ic_placeholder,
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    GlideImage(
                        imageModel = hit.userImageURL,
                        contentScale = ContentScale.Crop,
//                    placeHolder = ImageBitmap.imageResource(R.drawable.ic_app_placeholder),
                        alignment = Alignment.TopStart,
                        modifier = Modifier
//                            .fillMaxWidth()
                            .size(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text("User: ${hit.user}")
                }




                Text("Tags: ${hit.tags}")

                Divider(modifier = Modifier.padding(8.dp))

                Text("Likes: ${hit.likes}")
                Text("Downloaded (times): ${hit.downloads}")
                Text("Comments: ${hit.comments}")


                Divider(modifier = Modifier.padding(8.dp))




                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}




