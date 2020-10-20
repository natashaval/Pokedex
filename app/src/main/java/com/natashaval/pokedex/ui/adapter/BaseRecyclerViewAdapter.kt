package com.natashaval.pokedex.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by natasha.santoso on 20/10/20.
 */
//https://medium.com/@info.anikdey003/kotlin-recyclerview-in-a-proper-and-re-usable-way-bb14717daa93
abstract class BaseRecyclerViewAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private var list: ArrayList<T>? = arrayListOf()
  protected var itemClickListener: OnItemClickListener? = null

  interface OnItemClickListener {
    fun onItemClick(position: Int, view: View?)
  }

  fun addItems(items: ArrayList<T>) {
    this.list?.addAll(items)
    notifyDataSetChanged()
  }

  fun clear() {
    this.list?.clear()
    notifyDataSetChanged()
  }

  fun getItem(position: Int): T? {
    return this.list?.get(position)
  }

  fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
    this.itemClickListener = onItemClickListener
  }

  override fun getItemCount(): Int = list?.size ?: 0
}