package com.frood.app.domain.model

import com.frood.app.domain.model.common.Model

data class Indicator(
    val index: Int,
    val isActive: Boolean
) : Model {

    override fun isItemSameWith(value: Model): Boolean {
        return value is Indicator && value.index == this.index
    }

    override fun isContentSameWith(value: Model): Boolean {
        return value is Indicator && value == this
    }

}
