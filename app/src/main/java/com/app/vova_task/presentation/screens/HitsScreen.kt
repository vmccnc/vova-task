package com.app.vova_task.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.app.vova_task.R
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.presentation.components.LoadingUI
import com.app.vova_task.presentation.vm.HitsViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HitsMachinesScreen(
    vm: HitsViewModel = hiltViewModel(),
    clickMachine: (Hit) -> Unit
) {

    val hits: List<Hit> by vm.hits
    val isLoading: Boolean by vm.isLoading


    var currentWord: String by vm.currentWord


    Scaffold(

        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Coding Challenge") },

                )
        },

        ) { padding ->

        val tt = padding

        Column() {


            Column(Modifier.padding(8.dp)) {
                OutlinedTextField(
                    value = currentWord,
                    onValueChange = vm::setCurrentWord,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Enter your word") },
//                isError = gameUiState.isGuessedWordWrong,
                    textStyle = TextStyle(Color.Gray, 20.sp),
                    keyboardActions = KeyboardActions(onDone = {
                        vm.loadMachines(currentWord)
//                coroutineScope.launch {
//                    ff.bottomSheetState.collapse()
//                }
                    }),
                )
            }

            Screen0(isLoading, hits, clickMachine)
        }

    }
}


@Composable
fun Screen0(
    isLoading: Boolean,
    machines: List<Hit>,
    clickMachine: (Hit) -> Unit
) {

    when {
        isLoading -> LoadingUI()
        else -> {
            if (machines.isNotEmpty()) {
                LazyColumn() {

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    items(machines) {
                        HitCart(it, clickMachine)
                    }

                    item { Spacer(modifier = Modifier.height(60.dp)) }
                }
            } else {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Something with server or with internet. Please  turn on internet or try again in 5-10 minutes."
                )
            }

        }

    }

}


@Composable
fun HitCart(
    item: Hit,
    clickMachine: (Hit) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { clickMachine(item) },
        elevation = 4.dp
    ) {
        Column {

            Image(
                painter = rememberImagePainter(data = item.webformatURL) {
                    this.error(R.drawable.ic_broken_image)
                    this.placeholder(R.drawable.ic_placeholder)
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {
                Text("${item.user}", modifier = Modifier.padding( 8.dp))

                GlideImage(
                    imageModel = item.userImageURL,
                    contentScale = ContentScale.Crop,
//                    placeHolder = ImageBitmap.imageResource(R.drawable.ic_app_placeholder),
                    alignment = Alignment.TopStart,
                    modifier = Modifier
//                            .fillMaxWidth()
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Text(
                text = "Tags: ${item.tags}  ",
                modifier = Modifier.padding( 8.dp),
                style = MaterialTheme.typography.subtitle1
            )

        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContentGame2(vm: HitsViewModel, ff: BottomSheetScaffoldState) {

    val coroutineScope = rememberCoroutineScope()

    var gg by remember { mutableStateOf<String>("") }

    Box(
        Modifier
            .fillMaxWidth()
//            .height(500.dp)
            .padding(20.dp)
    ) {
        Column {


            OutlinedTextField(
                value = gg,
                onValueChange = {
                    gg = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter your word") },
//                isError = gameUiState.isGuessedWordWrong,
                textStyle = TextStyle(Color.Gray, 20.sp),
                keyboardActions = KeyboardActions(onDone = {
                    vm.loadMachines(gg)
                    coroutineScope.launch {
                        ff.bottomSheetState.collapse()
                    }
                }),
            )


            OutlinedButton(
                onClick = {
                    vm.loadMachines(gg)
                    coroutineScope.launch {
                        ff.bottomSheetState.collapse()
                    }
                },
                modifier = Modifier.padding(4.dp),

            ) {
                Text(
                    text = "Search",
                )
            }


        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun GameScreen2_Peview() {
//    Game2Screen() {
//
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Preview(showBackground = true)
//@Composable
//fun SheetContentGame2_Preview() {
//    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState = rememberBottomSheetState(
//            initialValue = BottomSheetValue.Collapsed
//        )
//    )
//    SheetContentGame2(SawingsViewModel(), bottomSheetScaffoldState)
//}
