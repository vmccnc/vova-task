package com.app.vova_task.presentation.vm


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.domain.repository.HitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitViewModel @Inject constructor(
    private val machineRepository: HitRepository
) : ViewModel() {

    private var _hit =
        mutableStateOf(Hit(0, "", "", "", 0, 0, "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", ""))
    var hit: State<Hit> = _hit


    fun getMachineById(machineId: Long) {
        viewModelScope.launch {
            _hit.value = machineRepository.oneFromDb(machineId).toHit()
        }

    }

}