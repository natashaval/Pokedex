package com.natashaval.pokedex.utils

import com.natashaval.pokedex.model.MyResponse
import retrofit2.Response
import timber.log.Timber

/**
 * Created by natasha.santoso on 19/10/20.
 */
object ResponseUtils {
  fun <T> convert(response: Response<in T>): MyResponse<T> {
    return try {
      if (response.isSuccessful) {
        MyResponse.success(response.body() as T)
      } else {
        Timber.e("Logging response failed: ${response.errorBody()}")
        MyResponse.failed(response.errorBody().toString())
      }
    } catch (t: Throwable) {
      Timber.e("Logging response error: ${t.message}")
      MyResponse.error(null, t.message)
    }
  }
}