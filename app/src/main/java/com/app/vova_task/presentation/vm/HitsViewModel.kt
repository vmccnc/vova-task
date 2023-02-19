package com.app.vova_task.presentation.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vova_task.data.local.toListHits
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.domain.repository.HitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HitsViewModel @Inject constructor(private val hitRepository: HitRepository) : ViewModel() {

    val hits = mutableStateOf<List<Hit>>(listOf())
    val isLoading = mutableStateOf<Boolean>(false)
    val isDialog = mutableStateOf<Boolean>(false)

    init {
        loadMachines("fruits")
    }


    fun loadMachines(str: String) {
        isLoading.value = true

        viewModelScope.launch() {

            try {
                isLoading.value = true

                val response = hitRepository.getSawings(str)

                if (response.isSuccessful) {

                    val listHits: List<Hit> = response.body()?.hits ?: emptyList()

                    listHits.forEach { Log.d("gg", "dm:: hit ${it.user}  ") }


                    if (listHits.isNotEmpty()) {
                        hitRepository.updateMachinesInDb(listHits.map { it.toHitEntity() })
                    }

                    hits.value = hitRepository.getMachineByModel(str).toListHits()

                } else {
                    isDialog.value = true
                    hits.value = hitRepository.getMachineByModel(str).toListHits()
                }


            } catch (e: IOException) {
                isDialog.value = true
                hits.value = hitRepository.getMachineByModel(str).toListHits()
            }

            isLoading.value = false
        }
    }

}