package ru.easycode.zerotoheroandroidtdd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [ItemCache::class])
abstract class ItemsDataBase : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao
}