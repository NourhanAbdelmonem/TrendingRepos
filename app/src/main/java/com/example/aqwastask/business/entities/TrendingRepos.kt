package com.example.aqwastask.business.entities


import com.google.gson.annotations.SerializedName

data class TrendingRepos(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    var items: List<Item>,
    @SerializedName("msg")
    val msg: String
)