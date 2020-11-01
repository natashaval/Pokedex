package com.natashaval.pokedex.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.Status
import kotlinx.coroutines.Dispatchers

/**
 * Created by natasha.santoso on 01/11/20.
 */
//https://itnext.io/android-architecture-hilt-mvvm-kotlin-coroutines-live-data-room-and-retrofit-ft-8b746cab4a06
//https://proandroiddev.com/android-architecture-starring-kotlin-coroutines-jetpack-mvvm-room-paging-retrofit-and-dagger-7749b2bae5f7
object DatabaseUtils {
  fun <T, A> performGetOperation(
      databaseQuery: () -> LiveData<T>,
      networkCall: suspend () -> MyResponse<A>,
      saveCallResult: suspend (A) -> Unit):
      LiveData<MyResponse<T>> = liveData(Dispatchers.IO) {
        emit(MyResponse.loading(null))
        val source = databaseQuery.invoke().map { MyResponse.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
          saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Status.ERROR) {
          emit(MyResponse.error(null, responseStatus.message!!))
          emitSource(source)
        }
  }
}