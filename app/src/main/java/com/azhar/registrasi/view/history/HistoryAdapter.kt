package com.azhar.registrasi.view.history

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.registrasi.R
import com.azhar.registrasi.model.ModelDatabase
import com.azhar.registrasi.utils.BitmapManager.base64ToBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.list_history_regis.view.*
import java.lang.String
import kotlin.Int


class HistoryAdapter(
    var mContext: Context,
    var modelDatabase: MutableList<ModelDatabase>,
    var mAdapterCallback: HistoryAdapterCallback) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun setDataAdapter(items: List<ModelDatabase>) {
        modelDatabase.clear()
        modelDatabase.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_history_regis, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelDatabase[position]
        holder.tvNomor.text = String.valueOf(data.uid)
        holder.tvNama.text = data.nama
        holder.tvLokasi.text = data.lokasi
        holder.tvAbsenTime.text = data.tanggal
        holder.tvStatusAbsen.text = data.keterangan

        Glide.with(mContext)
            .load(base64ToBitmap(data.fotoSelfie))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_photo_camera)
            .into(holder.imageProfile)

        when (data.keterangan) {
            "KIP Kuliah" -> {
                holder.colorStatus.setBackgroundResource(R.drawable.bg_circle_radius)
                holder.colorStatus.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
            }
            "Bank Indonesia" -> {
                holder.colorStatus.setBackgroundResource(R.drawable.bg_circle_radius)
                holder.colorStatus.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
            "Beasiswa Unggulan" -> {
                holder.colorStatus.setBackgroundResource(R.drawable.bg_circle_radius)
                holder.colorStatus.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
            }
        }
    }

    override fun getItemCount(): Int {
        return modelDatabase.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStatusAbsen: TextView = itemView.tvStatusAbsen
        var tvNomor: TextView = itemView.tvNomor
        var tvNama: TextView = itemView.tvNama
        var tvLokasi: TextView = itemView.tvLokasi
        var tvAbsenTime: TextView = itemView.tvAbsenTime
        var cvHistory: CardView = itemView.cvHistory
        var imageProfile: ShapeableImageView = itemView.imageProfile
        var colorStatus: View = itemView.colorStatus

        init {
            cvHistory.setOnClickListener {
                val modelLaundry = modelDatabase[adapterPosition]
                mAdapterCallback.onDelete(modelLaundry)
            }
        }
    }

    interface HistoryAdapterCallback {
        fun onDelete(modelDatabase: ModelDatabase?)
    }
}