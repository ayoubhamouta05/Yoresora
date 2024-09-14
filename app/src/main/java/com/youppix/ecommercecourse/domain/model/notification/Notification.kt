package com.youppix.ecommercecourse.domain.model.notification

data class Notification(
    val id : Int ,
    val title : String ,
    val body : String ,
    val time : String ,
    val unread : Boolean
)
