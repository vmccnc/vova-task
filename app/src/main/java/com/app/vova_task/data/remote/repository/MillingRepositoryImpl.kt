package com.app.vova_task.data.remote.repository

import android.util.Log
import com.app.vova_task.data.local.MachineEntity
import com.app.vova_task.data.local.dao.MillingDao
import com.app.vova_task.data.local.toListMachines
import com.app.vova_task.data.remote.RetrofitApiService
import com.app.vova_task.data.remote.dto.MachineDto
import com.app.vova_task.data.remote.dto.toMachineList
import com.app.vova_task.domain.model.Machine
import com.app.vova_task.domain.repository.MachinesRepository
import java.io.IOException
import javax.inject.Inject

class MillingsRepositoryImpl @Inject constructor(
    private val service: RetrofitApiService,
    private val millingDao: MillingDao
) : MachinesRepository {

    override suspend fun getSawings(str: String )  = service.listSawings(str)

//
//        try {
//            ff.invoke(true)
//            Log.d("gg", "dm:: EEEEEEEEEE")
//            val res1 =
//
//            if (res1.isSuccessful) {
//
//                val listDto1: List<MachineDto> = res1.body() ?: emptyList()
//
//                val machines: List<Machine> = listDto1.toMachineList()
//
//                machines.forEach {
//                    Log.d("gg", "dm:: machine saved:  ${it.model}  ")
//                }
//                ff.invoke(false)
////                return machines
//
//                if (machines.isNotEmpty()) {
//                    millingDao.saveAll(machines.map { it.toEntity() })
////                        machineRepository.updateMachinesInDb(machines.map { it.toEntity() })
//                    Log.d("gg", "dm:: All machines are saved in DB")
//                } else {
////                        machineRepository.deleteAllFromDb()
//                }
//
//                return millingDao.allMillings().toListMachines()
//            } else {
//                Log.d("gg", "dm::  Unable to get data from Internate / ")
//                Log.d("gg", "dm:: Error code = ${res1.code()} / ")
//                ff.invoke(false)
//                return millingDao.getMachineByModel(str).toListMachines()
//            }
//        } catch (e: IOException) {
//            Log.d("gg", "dm::  IOException: ${e.message}")
//            ff.invoke(false)
//
//            return millingDao.getMachineByModel(str).toListMachines()
//        }



    override suspend fun oneFromDb(machineId: String): MachineEntity =
        millingDao.getMachineByMachineId(machineId)


//    override suspend fun machinesFromDb(): List<MachineEntity> = millingDao.allMillings()
//
    override suspend fun getMachineByModel(type: String ): List<MachineEntity> =  millingDao.getMachineByModel(type,)
//
//
    override suspend fun updateMachinesInDb(machines: List<MachineEntity>) =
//        millingDao.deleteAndInsertInTransaction(machines)
        millingDao.saveAll(machines)
//
//    override suspend fun deleteAllFromDb() = millingDao.deleteAll()

}