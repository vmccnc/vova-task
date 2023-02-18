package com.app.vova_task.domain.model

import com.app.vova_task.data.local.MachineEntity


data class Machine(

    val machineId: Int = 0,
    var manufacturer: String? = null,
    var model: String = "",
    var city: String? = null,
    var description: String = "",
    var year: String? = null,
    var url: String = ""
) {


    fun toEntity(): MachineEntity =
        MachineEntity(
            machineId = machineId ,
            manufacturer = manufacturer ?: "",
            model = model ?: "",
            city = city ?: "",
            description = description ?: "",
            year = year ?: "",
            url = url
        )


}