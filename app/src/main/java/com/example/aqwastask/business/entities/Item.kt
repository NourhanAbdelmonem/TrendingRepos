package com.example.aqwastask.business.entities


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("added_stars")
    val addedStars: String,
    @SerializedName("avatars")
    val avatars: List<String>,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("forks")
    val forks: String,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("repo")
    val repo: String,
    @SerializedName("repo_link")
    val repoLink: String,
    @SerializedName("stars")
    val stars: String,
    var isExpanded: Boolean = false
)