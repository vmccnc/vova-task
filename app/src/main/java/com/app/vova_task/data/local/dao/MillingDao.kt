package com.app.vova_task.data.local.dao

import androidx.room.*
import com.app.vova_task.data.local.MachineEntity

@Dao
interface MillingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(list: List<MachineEntity>)

    @Query("SELECT * FROM machines where machineId = :machineId")
    suspend fun getMachineByMachineId(machineId: String): MachineEntity


    @Query("SELECT * FROM machines")
    suspend fun allMillings(): List<MachineEntity>

    @Query("SELECT * FROM machines where machineId = :machineId")
    suspend fun getMillingById(machineId: Int): MachineEntity

    @Query("SELECT * FROM machines where model  LIKE  '%' || :model || '%'")
    suspend fun getMachineByModel(model: String): List<MachineEntity>
//
//    @Query("SELECT * FROM machines where headline = :type OR headline = :type2")
//    suspend fun getMachineByType(type: String, type2: String ): List<MachineEntity>

    @Query("DELETE FROM machines")
    fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertInTransaction(list: List<MachineEntity>) {
        deleteAll()
        saveAll(list)
    }
}

