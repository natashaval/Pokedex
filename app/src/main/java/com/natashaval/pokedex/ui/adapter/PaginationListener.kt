package com.natashaval.pokedex.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by natasha.santoso on 21/10/20.
 */
//https://androidwave.com/pagination-in-recyclerview/
abstract class PaginationListener(private val layoutManager: LinearLayoutManager, private val pageSize: Int) : RecyclerView.OnScrollListener() {
  companion object {
    const val PAGE_START = 0
  }

  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)

    val visibleItemCount = layoutManager.childCount
    val totalItemCount = layoutManager.itemCount
    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

    if (!isLoading() && !isLastPage()) {
      if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
          firstVisibleItemPosition >= 0 &&
          totalItemCount >= pageSize) {
        loadMoreItems()
      }
    }
  }

  abstract fun isLoading() : Boolean
  abstract fun isLastPage() : Boolean
  abstract fun loadMoreItems()
}