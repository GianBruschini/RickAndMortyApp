package com.gian.rickandmortyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.gian.rickandmortyapp.databinding.CharacterItemBinding
import com.gian.rickandmortyapp.model.Results
import com.gian.rickandmortyapp.view.CharactersListActivitiy
import com.squareup.picasso.Picasso

class AdapterCharacters(var context:CharactersListActivitiy) : RecyclerView.Adapter<AdapterCharacters.MyViewHolder>() {
    var listOfCharacters = ArrayList<Results>()

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onitemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().
        load(listOfCharacters[position].image).
        into(holder.binding.imageOfCharacter)
        holder.binding.nameOfCharacter.text = listOfCharacters[position].name

        var left:Int = dpToPx(24)
        var top:Int = dpToPx(12)
        var right:Int = dpToPx(24)
        var bottom:Int = dpToPx(12)

        var spanCount = 2

        var isFirst2Items:Boolean = position < spanCount
        var isLast2Items:Boolean = position > itemCount - spanCount

        if(isFirst2Items){
            top = dpToPx(24)
        }

        if(isLast2Items){
            bottom = dpToPx(24)
        }

        var isLeftSide: Boolean = (position + 1 ) % spanCount != 0
        var isRightSide: Boolean = !isLeftSide

        if(isLeftSide){
            right = dpToPx(12)
        }

        if(isRightSide){
            left = dpToPx(12)
        }

        val layoutParamsTo = holder.binding.cardView1.layoutParams as FrameLayout.LayoutParams
        layoutParamsTo.setMargins(left, top, right, bottom)
        holder.binding.cardView1.layoutParams = layoutParamsTo


    }

    private fun dpToPx(dp: Int):Int{
        var px:Float = dp * context.resources.displayMetrics.density
        return px.toInt()
    }

    override fun getItemCount(): Int {
        return listOfCharacters.size
    }

    fun setUpdatedData(arrayList: java.util.ArrayList<Results>) {
        listOfCharacters.addAll(arrayList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: CharacterItemBinding):RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                if (mListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        mListener!!.onitemClick(position)
                    }
                }
            }
        }
    }



}