package com.natashaval.pokedex.utils

import android.net.Uri
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.utils.Constant.Companion.ITEM_SPRITE_URL
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
    } catch (e: Exception) {
      Timber.e("Logging response error: ${e.message}")
      MyResponse.error(null, e.message)
    }
  }

  fun buildUrl(path: String, offset: Int?, limit: Int?): String? {
    return Uri.parse(Constant.BASE_URL).buildUpon()
        .appendPath(path)
        .appendQueryParameter("offset", offset.toString())
        .appendQueryParameter("limit", limit.toString())
        .build().toString()
  }

  fun buildSpriteUrl(name: String?): String? {
    return Uri.parse(Constant.ITEM_SPRITE_URL).buildUpon()
        .appendPath(name).build().toString()
  }
}