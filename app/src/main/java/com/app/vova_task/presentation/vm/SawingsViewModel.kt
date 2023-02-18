package com.app.vova_task.presentation.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vova_task.data.local.toListMachines
import com.app.vova_task.data.remote.dto.MachineDto
import com.app.vova_task.data.remote.dto.toMachineList
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.domain.repository.MachinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SawingsViewModel @Inject constructor(
    private val machineRepository: MachinesRepository
) : ViewModel() {

    val machinesLive = mutableStateOf<List<Machine>>(listOf())
    val isLoading = mutableStateOf<Boolean>(false)
    val isDialog = mutableStateOf<Boolean>(false)

    init {
        loadMachines()
    }


    fun loadMachines(str: String = "11") {
        isLoading.value = true

        viewModelScope.launch() {

//            machinesLive.value = machineRepository.getSawings(str) {
//                isLoading.value = it
//            }

            try {

                Log.d("gg", "dm:: EEEEEEEEEE")
                val res1 = machineRepository.getSawings(str)

                if (res1.isSuccessful) {

                    val listDto1: List<MachineDto> = res1.body() ?: emptyList()

                    val machines: List<Machine> = listDto1.toMachineList()

                    machines.forEach {
                        Log.d("gg", "dm:: machine saved:  ${it.model}  ")
                    }


                    if (machines.isNotEmpty()) {
                        machineRepository.updateMachinesInDb(machines.map { it.toEntity() })
//                        machineRepository.updateMachinesInDb(machines.map { it.toEntity() })
                        Log.d("gg", "dm:: All machines are saved in DB")
                    } else {
//                        machineRepository.deleteAllFromDb()
                    }

                    machinesLive.value = machineRepository.getMachineByModel(str).toListMachines()
                } else {
                    Log.d("gg", "dm::  Unable to get data from Internate / ")
                    Log.d("gg", "dm:: Error code = ${res1.code()} / ")
//                    ff.invoke(false)
                    isDialog.value = true
                    machinesLive.value = machineRepository.getMachineByModel(str).toListMachines()
                }
            } catch (e: IOException) {
                Log.d("gg", "dm::  IOException: ${e.message}")
//                ff.invoke(false)

             val gg =    machineRepository.getMachineByModel(str).toListMachines()
                gg.forEach {
                    Log.d("gg", "dm:: machine from DB:  ${it.model}  ")
                }
                Log.d("gg", "dm:: machine size  ${gg.size}  ")
                isDialog.value = true
                machinesLive.value = machineRepository.getMachineByModel(str).toListMachines()
            }

            isLoading.value = false
        }
    }

}