package com.app.vova_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.vova_task.data.local.dao.HitDao


@Database(
    entities = arrayOf(
        HitEntity::class
    ),
    version = 3, exportSchema = false
)
abstract class AptDatabase : RoomDatabase() {

    abstract fun hitDao(): HitDao

    fun clearData() {
//        millingDao().deleteAll()
    }

}