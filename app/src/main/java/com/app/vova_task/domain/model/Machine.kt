package com.app.vova_task.domain.model




data class Machine(

    val machineId: Int = 0,
    var manufacturer: String? = null,
    var model: String = "",
    var city: String? = null,
    var description: String = "",
    var year: String? = null,
    var url: String = ""
) {


//    fun toEntity(): MachineEntity =
//        MachineEntity(
//            machineId = machineId ,
//            headline = headline ?: "",
//            manufacturer = manufacturer ?: "",
//            model = model ?: "",
//            category = category ?: "",
//            condition = condition ?: "",
//            city = city ?: "",
//            currency = currency ?: "",
//            description = description ?: "",
//            specSheet = specSheet ?: "",
//            stockNumber = stockNumber ?: "",
//            website = website ?: "",
//            year = year ?: "",
//            url = url,
//            images = images.joinToString(",")
//        )


}