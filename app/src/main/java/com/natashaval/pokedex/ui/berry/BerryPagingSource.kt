package com.natashaval.pokedex.ui.berry

import androidx.paging.PagingSource
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.repository.BerryRepository
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by natasha.santoso on 28/10/20.
 */
class BerryPagingSource constructor(private val repository: BerryRepository) :
    PagingSource<Int, NamedApiResource>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NamedApiResource> {
    return try {
      val currentPage = params.key ?: 0
      val offset = currentPage * BerryViewModel.BERRY_LIMIT

      val response = repository.getBerryList(offset, BerryViewModel.BERRY_LIMIT)
      val resource = response.data?.results
      LoadResult.Page(resource ?: listOf(), null,
        if (response.data?.next == null) null else currentPage + 1)
    } catch (ex: IOException) {
      LoadResult.Error(ex)
    } catch (ex: HttpException) {
      LoadResult.Error(ex)
    }
  }

}