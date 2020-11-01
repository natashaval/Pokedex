package com.natashaval.pokedex.interfaces

import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.model.NamedApiResource

/**
 * Created by natasha.santoso on 28/10/20.
 */
interface IActivityView {
  fun openItemBottomSheet(itemMode: Int, namedApiResource: NamedApiResource?)
  fun openTypeBottomSheet()
}