package com.app.vova_task

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.vova_task.data.local.AptDatabase
import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.local.dao.HitDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {

    private lateinit var itemDao: HitDao
    private lateinit var inventoryDatabase: AptDatabase

    private val item1 =
        HitEntity(1, "", "eee", "", 0, 0, "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "")

    private val item2 =
        HitEntity(2, "", "rr", "", 0, 0, "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "")


    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, AptDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.hitDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        inventoryDatabase.close()
    }


    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems: List<HitEntity> = itemDao.getAllHits().first()
        assertEquals(allItems[0], item1)
    }


    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
    }


}