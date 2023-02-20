package com.app.vova_task.presentation.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.domain.repository.HitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HitsViewModel @Inject constructor(private val hitRepository: HitRepository) : ViewModel() {

    val hits: State<List<Hit>> = hitRepository.hits
    val isLoading = mutableStateOf<Boolean>(false)

    val currentWord = mutableStateOf("fruits")

    init {
        loadMachines(currentWord.value)
    }


    fun setCurrentWord(str: String){
        currentWord.value = str
    }

    fun loadMachines(str: String) {

        hitRepository.loadMachines(getStrWithPlus(str), viewModelScope) {
            isLoading.value = it
        }

    }


    internal fun getStrWithPlus(str: String): String =
        if (str.isEmpty()) "fruits"
        else str.trim().split("\\s+".toRegex()).joinToString("+")

}