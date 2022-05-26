package com.frood.app.domain.model

import com.frood.app.domain.model.common.Model

data class Product(
    val id: Long,
    val name: String,
    val imageSrc: Int,
    val roomTemp: Long,
    val FridgeTemp: Long
) : Model
