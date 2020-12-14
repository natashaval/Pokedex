package com.natashaval.pokedex.ui.type

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.natashaval.pokedex.R
import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.databinding.ItemButtonBinding
import com.natashaval.pokedex.utils.Constant
import com.natashaval.base.utils.setSafeClickListener

/**
 * Created by natasha.santoso on 01/11/20.
 */

class TypeAdapter constructor(private val context: Context, private val typeList: List<EntityType>,
    private val listener: (EntityType?) -> Unit) : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: TypeAdapter.ViewHolder, position: Int) {
    holder.bind(context, typeList[position], listener)
  }

  override fun getItemCount(): Int = typeList.size

  inner class ViewHolder internal constructor(v: View) :
      RecyclerView.ViewHolder(v) {
    private var itemBinding: ItemButtonBinding? = null

    init {
      itemBinding = ItemButtonBinding.bind(v)
    }

    fun bind(context: Context, type: EntityType, listener: (EntityType?) -> Unit) {
      itemBinding?.run {
        btType.text = if (type.name.isNotEmpty()) type.name else "all types"
        btType.setSafeClickListener { listener(type) }

        val color = ContextCompat.getColor(context,
            Constant.colorMap[type.name] ?: R.color.colorAccent)
        btType.setBackgroundColor(color)
      }
    }
  }
}