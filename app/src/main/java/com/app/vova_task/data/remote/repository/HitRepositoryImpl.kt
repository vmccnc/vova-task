package com.app.vova_task.data.remote.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.local.dao.HitDao
import com.app.vova_task.data.local.toListHits
import com.app.vova_task.data.remote.RetrofitApiService
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.data.remote.dto.Welcome10
import com.app.vova_task.domain.repository.HitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class HitRepositoryImpl @Inject constructor(
    private val service: RetrofitApiService,
    private val hitDao: HitDao
) : HitRepository {

    override val hits: MutableState<List<Hit>> = mutableStateOf<List<Hit>>(listOf())

    override suspend fun getSawings(str: String): Response<Welcome10> = service.listSawings(q = str)


    override   fun loadMachines(str: String, scope: CoroutineScope) {


        val list: List<String> = str.split(" ")

//        isLoading.value = true
        scope.launch {


            try {
//                isLoading.value = true

                var listHits: List<Hit> = emptyList()

                if (list.size == 1) {

                    val response = getSawings(str)
                    if (response.isSuccessful) {
                        listHits = response.body()?.hits ?: emptyList()
                        listHits.forEach { Log.d("gg", "dm:: hit ${it.user}  ") }
                    }

                } else {
                    val response1 = async { service.listSawings(q = list[0]) }
                    val response2 = async { service.listSawings(q = list[1]) }

                    val res1 = response1.await()
                    val res2 = response2.await()

//                    if (res1.isSuccessful  ||  res2.isSuccessful ) {
                        val listDto1: List<Hit> = res1.body()?.hits ?: emptyList()
                        val listDto2: List<Hit> = res2.body()?.hits ?: emptyList()
                        listHits = listDto1.plus(listDto2)
//                    }
                }

                Log.d("gg", "dm:: size of list with Hits = ${listHits.size} ")

                if (listHits.isNotEmpty()) {
                    hitDao.saveAll(listHits.map { it.toHitEntity() })
                }

//                hitDao.getHitByModel(str).toListHits().forEach {
//                    Log.d("gg", "dm:: hit W ${it.user}  ")
//                }

                if (list.size == 1) {
                    hits.value = hitDao.getHitByModel(list[0]).toListHits()
                }else{
                    Log.d("gg", "dm:: size = ${list.size} / '${list[0]}' - '${list[1]}'  ")
                    hits.value = hitDao.getHitByModel(list[0], list[1], ).toListHits()
                }



            } catch (e: IOException) {
//                isDialog.value = true
                hits.value = hitDao.getHitByModel(str).toListHits()
            }

//            isLoading.value = false
        }
    }


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
//    override suspend fun getMachineByModel(type: String): List<HitEntity> =
//        hitDao.getHitByModel(type)
//
//    //
////
//    override suspend fun updateMachinesInDb(machines: List<HitEntity>) =
//        hitDao.saveAll(machines)
////

}