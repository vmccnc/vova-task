package com.app.vova_task.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.app.vova_task.R
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.presentation.components.LoadingUI
import com.app.vova_task.presentation.vm.SawingsViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SawingsMachinesScreen(
    vm: SawingsViewModel = hiltViewModel(),
    clickMachine: (Machine) -> Unit
) {
    val machines: MutableState<List<Machine>> = vm.machinesLive
    val isLoading: Boolean by vm.isLoading

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
    )



    val coroutineScope = rememberCoroutineScope()


    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { SheetContentGame2(vm, bottomSheetScaffoldState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Sawing machines") },
                actions = {
                    IconButton(onClick = {
                        keyboardController?.hide()
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Checklist, contentDescription = null)
                    }
                }
            )
        },
        sheetShape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
        sheetElevation = 20.dp,
        sheetPeekHeight = 0.dp
    ) { padding ->

        val tt = padding
        Screen0(isLoading, machines.value, clickMachine)
    }
}


@Composable
fun Screen0(
    isLoading: Boolean,
//    isError: Boolean,
    machines: List<Machine>,
    clickMachine: (Machine) -> Unit
) {

    when {
        isLoading -> LoadingUI()
//        isError -> ErrorUI()
        else -> {
            if (machines.isNotEmpty()) {
                LazyColumn() {

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    items(machines) {
                        MachineCart(it, clickMachine)
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
fun MachineCart(
    item: Machine,
    clickMachine: (Machine) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { clickMachine(item) },
        elevation = 4.dp
    ) {
        Column {

            Image(
                painter = rememberImagePainter(data = item.url) {
                    this.error(R.drawable.ic_broken_image)
                    this.placeholder(R.drawable.ic_placeholder)
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "${item.model} (${item.manufacturer}) ",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                style = MaterialTheme.typography.subtitle1
            )

        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetContentGame2(vm: SawingsViewModel, ff: BottomSheetScaffoldState) {

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
