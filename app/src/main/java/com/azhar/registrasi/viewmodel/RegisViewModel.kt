package com.azhar.registrasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azhar.registrasi.database.DatabaseClient.Companion.getInstance
import com.azhar.registrasi.database.dao.DatabaseDao
import com.azhar.registrasi.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class RegisViewModel(application: Application) : AndroidViewModel(application) {
    var databaseDao: DatabaseDao? = getInstance(application)?.appDatabase?.databaseDao()

    fun addDataAbsen(
        foto: String, nama: String,
        tanggal: String, lokasi: String, keterangan: String) {
        Completable.fromAction {
            val modelDatabase = ModelDatabase()
            modelDatabase.fotoSelfie = foto
            modelDatabase.nama = nama
            modelDatabase.tanggal = tanggal
            modelDatabase.lokasi = lokasi
            modelDatabase.keterangan = keterangan
            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}