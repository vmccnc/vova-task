package com.app.vova_task.data.remote.repository

import android.util.Log
import com.app.vova_task.data.remote.RetrofitApiService
import com.app.vova_task.data.remote.dto.MachineDto
import com.app.vova_task.data.remote.dto.toMachineList
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.domain.repository.MachinesRepository
import java.io.IOException
import javax.inject.Inject

class MillingsRepositoryImpl @Inject constructor(
    private val service: RetrofitApiService,
//    private val millingDao: MillingDao
) : MachinesRepository {

    override suspend fun getSawings(str: String, ff: (Boolean) -> Unit): List<Machine> {


        try {
            ff.invoke(true)
            Log.d("gg", "dm:: EEEEEEEEEE")
            val res1 = service.listSawings(str)

            if (res1.isSuccessful) {

                val listDto1: List<MachineDto> = res1.body() ?: emptyList()

                val machines: List<Machine> = listDto1.toMachineList()

                machines.forEach {
                    Log.d("gg", "dm:: machine saved:  ${it.model}  ")
                }
                ff.invoke(false)
                return machines

//                if (machines.isNotEmpty()) {
//                    millingDao.deleteAndInsertInTransaction(machines.map { it.toEntity() })
////                        machineRepository.updateMachinesInDb(machines.map { it.toEntity() })
//                    Log.d("gg", "dm:: All machines are saved in DB")
//                } else {
////                        machineRepository.deleteAllFromDb()
//                }


            } else {
                Log.d("gg", "dm::  Unable to get data from Internate / ")
                Log.d("gg", "dm:: Error code = ${res1.code()} / ")
                ff.invoke(false)
                return emptyList()
            }
        } catch (e: IOException) {
            Log.d("gg", "dm::  IOException: ${e.message}")
            ff.invoke(false)
            return emptyList()
        }


    }


//    override suspend fun oneFromDb(machineId: String): MachineEntity =
//        millingDao.getMachineByMachineId(machineId)
//
//
//    override suspend fun machinesFromDb(): List<MachineEntity> = millingDao.allMillings()
//
//    override suspend fun machinesByType(type: String, type2: String?): List<MachineEntity> =
//        if (type2 != null) millingDao.getMachineByType(type, type2)
//        else millingDao.getMachineByType(type)
//
//
//    override suspend fun updateMachinesInDb(machines: List<MachineEntity>) =
//        millingDao.deleteAndInsertInTransaction(machines)
//
//    override suspend fun deleteAllFromDb() = millingDao.deleteAll()

}