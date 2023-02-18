package com.app.vova_task.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.vova_task.domain.model.Machine


@Entity(tableName = "machines")
class MachineEntity(
//    @field:PrimaryKey(autoGenerate = true)
//    var id: Long = 0,
    @PrimaryKey(autoGenerate = false)
    var machineId: Int,
    var manufacturer: String?,
    var model: String?,
    var city: String?,
    var description: String?,
    var year: String?,
    var url: String
    ) {

    fun toMachine() =
        Machine(
            machineId = machineId,
            manufacturer = manufacturer ?: "",
            model = model ?: "",
            city = city ?: "",
            description = description ?: "",
            year = year ?: "",
            url = url,
        )
}

fun List<MachineEntity>.toListMachines(): List<Machine> = this.map { it.toMachine() }