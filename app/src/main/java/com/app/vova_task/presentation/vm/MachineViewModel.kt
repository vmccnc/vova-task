package com.app.vova_task.presentation.vm


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.domain.repository.MachinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MachineViewModel @Inject constructor(
    private val machineRepository: MachinesRepository
) : ViewModel() {

    private var _machine = mutableStateOf(Machine())
    var machine: State<Machine> = _machine


    fun getMachineById(machineId: String) {
        viewModelScope.launch {
            _machine.value = machineRepository.oneFromDb(machineId).toMachine()
        }

    }

}