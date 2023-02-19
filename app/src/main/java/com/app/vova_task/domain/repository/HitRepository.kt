package com.app.vova_task.domain.repository

import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.remote.dto.Welcome10
import retrofit2.Response


interface HitRepository {

//    val machinesLive: MutableState<List<Machine>>

    suspend fun getSawings(str: String): Response<Welcome10>


    // DB
    suspend fun oneFromDb(machineId: Long): HitEntity

    suspend fun updateMachinesInDb(machines: List<HitEntity>)
    suspend fun getMachineByModel(machines: String): List<HitEntity>
}