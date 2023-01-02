package com.azhar.registrasi.database

import androidx.room.Database
import com.azhar.registrasi.model.ModelDatabase
import androidx.room.RoomDatabase
import com.azhar.registrasi.database.dao.DatabaseDao


@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}