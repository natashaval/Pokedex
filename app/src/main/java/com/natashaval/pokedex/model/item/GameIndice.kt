package com.natashaval.pokedex.model.item

import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index") val gameIndex: Int? = null,
    @SerializedName("generation") val generation: Generation? = null
)