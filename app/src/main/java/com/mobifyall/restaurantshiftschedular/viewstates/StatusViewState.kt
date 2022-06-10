package com.mobifyall.restaurantshiftschedular.viewstates

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class StatusViewState : BaseObservable() {

    @Bindable
    var countTrashedOrder: Int = 0

    @Bindable
    var countDeliveredOrder: Int = 0

    @Bindable
    var totalSales: Int = 0

    @Bindable
    var totalWaste: Int = 0

    @Bindable
    var totalRevenue: Int = 0

    fun updateData(
        countTrashedOrder: Int = 0,
        countDeliveredOrder: Int = 0,
        totalSales: Int = 0,
        totalWaste: Int = 0,
        totalRevenue: Int = 0
    ) {
        this.countTrashedOrder = countTrashedOrder
        this.countDeliveredOrder = countDeliveredOrder
        this.totalSales = totalSales
        this.totalWaste = totalWaste
        this.totalRevenue = totalRevenue
        notifyChange()
    }
}