package com.app.vova_task.domain.repository

import com.app.vova_task.domain.model.Machine


interface MachinesRepository {

    suspend fun getSawings(str: String, ff: (Boolean) -> Unit): List<Machine>



    // DB
//    suspend fun oneFromDb(machineId: String): MachineEntity
//    suspend fun machinesFromDb(): List<MachineEntity>
//
//    //    suspend fun machinesByType(type: String): List<MachineEntity>
//    suspend fun machinesByType(type: String, type2: String? = null): List<MachineEntity>
//
//    suspend fun updateMachinesInDb(machines: List<MachineEntity>)
//    suspend fun deleteAllFromDb()
}