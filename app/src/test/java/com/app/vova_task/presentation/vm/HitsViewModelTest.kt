package com.app.vova_task.presentation.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.app.vova_task.data.local.HitEntity
import com.app.vova_task.data.remote.dto.Hit
import com.app.vova_task.domain.repository.HitRepository
import kotlinx.coroutines.CoroutineScope
import org.junit.Assert
import org.junit.Test


internal class HitsViewModelTest {


    private val hitsViewModel = HitsViewModel(object : HitRepository {
        override val hits: MutableState<List<Hit>> = mutableStateOf(emptyList())

        override fun loadMachines(str: String, scope: CoroutineScope, isLoading: (Boolean) -> Unit) {}

        override suspend fun oneFromDb(machineId: Long): HitEntity =
            HitEntity(0, "", "", "", 0, 0, "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "")
    })

    @Test
    fun getStrWithPlus1() {
        val strNew = hitsViewModel.getStrWithPlus(" fff  uuu")
        println("vo:: Result 1: '$strNew'")
        Assert.assertEquals("fff+uuu", strNew)
    }


    @Test
    fun getStrWithPlus2() {
        val strNew = hitsViewModel.getStrWithPlus(" fff   uuu  ")
        println("vo:: Result 2: '$strNew'")
        Assert.assertEquals("fff+uuu", strNew)
    }


    @Test
    fun getStrWithPlus3() {
        val strNew = hitsViewModel.getStrWithPlus("fff ")
        println("vo:: Result 3: '$strNew'")
        Assert.assertEquals("fff", strNew)
    }

    @Test
    fun getStrWithPlus4() {
        val strNew = hitsViewModel.getStrWithPlus("fff")
        println("vo:: Result 4: '$strNew'")
        Assert.assertEquals("fff", strNew)
    }

    @Test
    fun getStrWithPlus5() {
        val strNew = hitsViewModel.getStrWithPlus("")
        println("vo:: Result 5: '$strNew'")
        Assert.assertEquals("fruits", strNew)
    }
}