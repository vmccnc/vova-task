package com.app.vova_task.data.local.dao

import androidx.room.*
import com.app.vova_task.data.local.HitEntity

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(list: List<HitEntity>)

    @Query("SELECT * FROM hits where id = :machineId")
    suspend fun getHitByHitId(machineId: Long): HitEntity


    @Query("SELECT * FROM hits")
    suspend fun allMillings(): List<HitEntity>

    @Query("SELECT * FROM hits where id = :machineId")
    suspend fun getMillingById(machineId: Int): HitEntity

    @Query("SELECT * FROM hits where tags  LIKE  '%' || :tag || '%'")
    suspend fun getHitByModel(tag: String): List<HitEntity>
//
//    @Query("SELECT * FROM hits where headline = :type OR headline = :type2")
//    suspend fun getHitByType(type: String, type2: String ): List<HitEntity>

    @Query("DELETE FROM hits")
    fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertInTransaction(list: List<HitEntity>) {
        deleteAll()
        saveAll(list)
    }
}

