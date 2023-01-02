package com.azhar.registrasi.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.azhar.registrasi.R
import com.azhar.registrasi.utils.SessionLogin
import com.azhar.registrasi.view.regis.RegisActivity
import com.azhar.registrasi.view.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var strTitle: String
    lateinit var session: SessionLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitLayout()
    }

    private fun setInitLayout() {
        session = SessionLogin(this)
        session.checkLogin()

        cvAbsenMasuk.setOnClickListener {
            strTitle = "KIP Kuliah"
            val intent = Intent(this@MainActivity, RegisActivity::class.java)
            intent.putExtra(RegisActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvAbsenKeluar.setOnClickListener {
            strTitle = "Bank Indonesia"
            val intent = Intent(this@MainActivity, RegisActivity::class.java)
            intent.putExtra(RegisActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvPerizinan.setOnClickListener {
            strTitle = "Beasiswa Unggulan"
            val intent = Intent(this@MainActivity, RegisActivity::class.java)
            intent.putExtra(RegisActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

        imageLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Yakin Anda ingin Logout?")
            builder.setCancelable(true)
            builder.setNegativeButton("Batal") { dialog, which -> dialog.cancel() }
            builder.setPositiveButton("Ya") { dialog, which ->
                session.logoutUser()
                finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

}