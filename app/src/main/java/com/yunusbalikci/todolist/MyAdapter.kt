package com.yunusbalikci.todolist

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val mContext:Context,private val gelenListe:List<Notes>):RecyclerView.Adapter<MyAdapter.CardViewTasarimTutucu>() {


    inner class CardViewTasarimTutucu(view:View):RecyclerView.ViewHolder(view){

        var cardView:CardView
        var logo_id:ImageView
        var title_id:TextView

        init {
            cardView = view.findViewById(R.id.cardView)
            logo_id = view.findViewById(R.id.logo_id)
            title_id = view.findViewById(R.id.textView)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_view,parent,false)
        return CardViewTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardViewTasarimTutucu, position: Int) {
        val note = gelenListe[position]
        holder.title_id.text = note.note_name

    }

    override fun getItemCount(): Int {
        return gelenListe.size
    }


}