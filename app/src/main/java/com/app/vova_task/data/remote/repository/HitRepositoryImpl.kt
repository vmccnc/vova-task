package com.app.vova_task.data.remote.repository

import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.local.dao.HitDao
import com.app.vova_task.data.remote.RetrofitApiService
import com.app.vova_task.data.remote.dto.Welcome10
import com.app.vova_task.domain.repository.HitRepository
import retrofit2.Response
import javax.inject.Inject

class HitRepositoryImpl @Inject constructor(
    private val service: RetrofitApiService,
    private val hitDao: HitDao
) : HitRepository {

//    override val machinesLive = mutableStateOf<List<Machine>>(listOf())

    override suspend fun getSawings(str: String): Response<Welcome10> = service.listSawings(q = str)

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


    override suspend fun oneFromDb(machineId: Long): HitEntity =
        hitDao.getHitByHitId(machineId)

    //
    override suspend fun getMachineByModel(type: String): List<HitEntity> =
        hitDao.getHitByModel(type)

    //
//
    override suspend fun updateMachinesInDb(machines: List<HitEntity>) =
        hitDao.saveAll(machines)
//

}