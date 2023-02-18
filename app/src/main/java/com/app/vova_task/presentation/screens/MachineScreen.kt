package com.app.vova_task.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.presentation.vm.MachineViewModel
import com.skydoves.landscapist.glide.GlideImage


@ExperimentalFoundationApi
@Composable
fun MachineScreen(
    machineId: String?,
    vm: MachineViewModel = hiltViewModel(),
    navBack: () -> Unit
) {
    val machine: Machine by vm.machine

    machineId?.let {
        vm.getMachineById(it)
    }

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(machine.model, Modifier.fillMaxWidth())
                },
                navigationIcon = {
                    IconButton(onClick = { navBack() }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                    }
                },

                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 1.dp,
                actions = {
                    IconButton(onClick = {
                        context.startActivity(
                            Intent.createChooser(
                                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
                                    .apply {
                                        putExtra(
                                            Intent.EXTRA_EMAIL,
                                            arrayOf("akordirect@gmail.com")
                                        )
                                        putExtra(
                                            Intent.EXTRA_SUBJECT,
                                            "Request from Android application"
                                        )
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            "I am interested in: ${machine.model}, ${machine.description}"
                                        )
                                    },
                                "Send request for machines."
                            )
                        )
                    }) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(start = 8.dp, end = 8.dp)) {
            item {


                GlideImage(
                    imageModel = machine.url,
                    contentScale = ContentScale.Crop,
//                    placeHolder = ImageBitmap.imageResource(R.drawable.ic_app_placeholder),
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(machine.model)

                Divider(modifier = Modifier.padding(8.dp))

                Text("Description:")

                Text(machine.description)
                Divider(modifier = Modifier.padding(8.dp))

                Text("Specification:")



                Divider(modifier = Modifier.padding(8.dp))



                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}



