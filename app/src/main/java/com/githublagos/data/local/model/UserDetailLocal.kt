package com.githublagos.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.githublagos.data.constants.Constants.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
data class UserDetailLocal(
    @PrimaryKey
    val login: String,
    val avatarUrl: String?,
    val htmlUrl: String,
    val name: String?,
    val location: String?,
    val followers: Int?,
    val following: Int?,
    val repos: Int?,
    val bio: String?
)