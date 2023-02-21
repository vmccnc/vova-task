package com.app.vova_task.data.local.dao

import androidx.room.*
import com.app.vova_task.data.local.HitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: HitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(list: List<HitEntity>)

    @Query("SELECT * FROM hits where id = :machineId")
    suspend fun getHitByHitId(machineId: Long): HitEntity


    @Query("SELECT * FROM hits")
    fun getAllHits(): Flow<List<HitEntity>>

    @Query("SELECT * FROM hits where id = :machineId")
    suspend fun getMillingById(machineId: Int): HitEntity

    @Query("SELECT * FROM hits where tags  LIKE  '%' || :tag || '%'")
    suspend fun getHitByModel(tag: String): List<HitEntity>

    @Query("SELECT * FROM hits where (tags  LIKE  '%' || :tag1 || '%' or tags LIKE '%' || :tag2 || '%')")
    suspend fun getHitByModel(tag1: String, tag2: String): List<HitEntity>


    @Query("SELECT * FROM hits where (tags  LIKE  '%' || :tag1 || '%' or tags LIKE '%' || :tag2 || '%' or tags LIKE '%' || :tag3 || '%')")
    suspend fun getHitByModel(tag1: String, tag2: String, tag3: String): List<HitEntity>





    @Query("DELETE FROM hits")
    fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertInTransaction(list: List<HitEntity>) {
        deleteAll()
        saveAll(list)
    }
}

