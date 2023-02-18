package com.app.vova_task.domain.repository

import com.app.vova_task.data.local.MachineEntity
import com.app.vova_task.data.remote.dto.MachineDto
import com.app.vova_task.domain.model.Machine
import retrofit2.Response


interface MachinesRepository {

    suspend fun getSawings(str: String ): Response<List<MachineDto>>



    // DB
    suspend fun oneFromDb(machineId: String): MachineEntity
//    suspend fun machinesFromDb(): List<MachineEntity>
//
//    //    suspend fun machinesByType(type: String): List<MachineEntity>
//    suspend fun machinesByType(type: String, type2: String? = null): List<MachineEntity>
//
    suspend fun updateMachinesInDb(machines: List<MachineEntity>)
    suspend fun getMachineByModel(machines: String):List<MachineEntity>
//    suspend fun deleteAllFromDb()
}