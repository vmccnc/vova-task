package com.app.vova_task.data.remote.dto

import com.app.vova_task.domain.model.Machine
import com.squareup.moshi.Json

class MachineDto(
    @Json(name = "id") var machineId: Int,
    var manufacturer: String?,
    var model: String?,
    var city: String?,
    var description: String?,
    var year: String?,
    @Json(name = "url") var url: String,
) {

    fun toMachine() =
        Machine(
            machineId = machineId,
            manufacturer = manufacturer ?: "",
            model = model ?: "",
            city = city ?: "",
            description = description ?: "",
            year = year ?: "",
            url = url
        )

}

fun List<MachineDto>.toMachineList() = this.map { it.toMachine() }

