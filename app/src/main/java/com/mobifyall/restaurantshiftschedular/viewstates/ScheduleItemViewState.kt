package com.mobifyall.restaurantshiftschedular.viewstates

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ScheduleItemViewState : BaseObservable() {

    @get:Bindable
    var userDetails: String? = null

    @get:Bindable
    var color: Int? = Color.GREEN

    @get:Bindable
    var scheduledTime: String? = null

    fun updateData(userDetails: String, colorDrawable: Drawable?, time: String) {
//        this.color = colorDrawable
        this.userDetails = userDetails
        this.scheduledTime = time
        notifyChange()
    }
}