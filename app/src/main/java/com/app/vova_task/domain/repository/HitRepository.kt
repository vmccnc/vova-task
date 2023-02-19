package com.app.vova_task.domain.repository

import androidx.compose.runtime.MutableState
import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.data.remote.dto.Welcome10
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response


interface HitRepository {

    val hits: MutableState<List<Hit>>

    suspend fun getSawings(str: String): Response<Welcome10>

      fun loadMachines(str: String, scope: CoroutineScope): Unit


    // DB
    suspend fun oneFromDb(machineId: Long): HitEntity
//
//    suspend fun updateMachinesInDb(machines: List<HitEntity>)
//    suspend fun getMachineByModel(machines: String): List<HitEntity>
}