package com.natashaval.pokedex.model.location

import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.VersionGroup

data class Region(
        @SerializedName("id") val id: Int? = null,
        @SerializedName("locations") val locations: List<Location>? = null,
        @SerializedName("main_generation") val mainGeneration: MainGeneration? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("pokedexes") val pokedexes: List<Pokedex>? = null,
        @SerializedName("version_groups") val versionGroups: List<VersionGroup>? = null
)