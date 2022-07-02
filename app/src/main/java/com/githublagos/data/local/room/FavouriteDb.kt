package com.githublagos.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.githublagos.data.local.model.UserDetailLocal

@Database(
    entities = [UserDetailLocal::class], // Tell the database the entries will hold data of this type
    version = 1
)
abstract class FavouriteDb : RoomDatabase() {

    abstract fun getDao(): FavouriteDao
}