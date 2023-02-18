package com.app.vova_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.vova_task.data.local.dao.MillingDao


@Database(
    entities = arrayOf(
        MachineEntity::class
    ),
    version = 2, exportSchema = false
)
abstract class AptDatabase : RoomDatabase() {

    abstract fun millingDao(): MillingDao

    fun clearData() {
        millingDao().deleteAll()
    }

}