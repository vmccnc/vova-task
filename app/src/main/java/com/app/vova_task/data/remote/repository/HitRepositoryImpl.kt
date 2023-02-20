package com.app.vova_task.data.remote.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.local.dao.HitDao
import com.app.vova_task.data.local.toListHits
import com.app.vova_task.data.remote.RetrofitApiService
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.domain.repository.HitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class HitRepositoryImpl @Inject constructor(
    private val service: RetrofitApiService,
    private val hitDao: HitDao
) : HitRepository {

    override val hits: MutableState<List<Hit>> = mutableStateOf<List<Hit>>(listOf())


    override fun loadMachines(
        tagsWithPlus: String,
        scope: CoroutineScope,
        isLoading: (Boolean) -> Unit
    ) {

        val listOfTags: List<String> = tagsWithPlus.split("+")

        scope.launch() {


            try {
                isLoading.invoke(true)

                var listHits: List<Hit> = emptyList()


                val response = service.listHits(q = tagsWithPlus)

                if (response.isSuccessful) {
                    listHits = response.body()?.hits ?: emptyList()
                    listHits.forEach { Log.d("gg", "dm:: hit ${it.user}  ") }


                    if (listHits.isNotEmpty())
                        hitDao.saveAll(listHits.map { it.toHitEntity() })

                }

                Log.d("gg", "dm:: size of list with Hits = ${listHits.size} ")

             } catch (e: IOException) {
                Log.d("gg", "dm::  IOExeption = $e  ")

            } catch (e: Exception) {
                Log.d("gg", "dm::  Exeption = $e  ")

            } finally {
                if (listOfTags.size == 1) {
                    hits.value = hitDao.getHitByModel(listOfTags[0]).toListHits()
                }else if (listOfTags.size == 2){
                    Log.d("gg", "dm:: size = ${listOfTags.size} / '${listOfTags[0]}' - '${listOfTags[1]}'  ")
                    hits.value = hitDao.getHitByModel(listOfTags[0], listOfTags[1], ).toListHits()
                } else{
                    hits.value = hitDao.getHitByModel(listOfTags[0], listOfTags[1], listOfTags[2], ).toListHits()
                }
            }
            isLoading.invoke(false)


        }
    }


    override suspend fun oneFromDb(machineId: Long): HitEntity = hitDao.getHitByHitId(machineId)


}