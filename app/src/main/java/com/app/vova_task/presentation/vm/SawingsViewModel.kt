package com.app.vova_task.presentation.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.domain.repository.MachinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SawingsViewModel @Inject constructor(
    private val machineRepository: MachinesRepository
) : ViewModel() {

    val machinesLive = mutableStateOf<List<Machine>>(listOf())
    val isLoading = mutableStateOf<Boolean>(false)

    init {
        loadMachines()
    }


    fun loadMachines(str: String = "Iphone") {
        isLoading.value = true

        viewModelScope.launch() {

            machinesLive.value = machineRepository.getSawings(str) {
                isLoading.value = it
            }
            isLoading.value = false
        }
    }

}