package com.githublagos.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.githublagos.data.constants.Constants.TABLE_NAME
import com.githublagos.data.local.model.UserDetailLocal


@Dao
interface FavouriteDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getFavourites(): List<UserDetailLocal>

    @Delete
    suspend fun removeFromFavourite(user: UserDetailLocal)

    @Insert
    suspend fun addToFavourites(users: UserDetailLocal)

    @Query("SELECT * FROM $TABLE_NAME WHERE login LIKE :login LIMIT 1")
    suspend fun getUserFromDb(login: String): UserDetailLocal
}