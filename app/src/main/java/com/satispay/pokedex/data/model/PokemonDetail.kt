package com.satispay.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    // @SerializedName("abilities") val abilities: List<Ability>,
    // @SerializedName("base_experience") val baseExperience: Int,
    // @SerializedName("cries") val cries: Cries,
    // @SerializedName("forms") val forms: List<NameUrl>,
    // @SerializedName("game_indices") val gameIndices: List<GameIndex>,
    // @SerializedName("height") val height: Int,
    // @SerializedName("held_items") val heldItems: List<HeldItem>,
    @SerializedName("id") val id: Int,
    // @SerializedName("is_default") val isDefault: Boolean,
    // @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    // @SerializedName("moves") val moves: List<MoveDetail>,
    @SerializedName("name") val name: String,
    // @SerializedName("order") val order: Int,
    // @SerializedName("past_abilities") val pastAbilities: List<Any>,
    // @SerializedName("past_types") val pastTypes: List<Any>,
    @SerializedName("species") val species: NameUrl,
    @SerializedName("sprites") val sprites: Sprites,
    // @SerializedName("stats") val stats: List<StatDetail>,
    @SerializedName("types") val types: List<TypeSlot>,
    // @SerializedName("weight") val weight: Int
)

data class Ability(
    @SerializedName("ability") val ability: NameUrl,
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("slot") val slot: Int
)

data class NameUrl(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class Cries(
    @SerializedName("latest") val latest: String,
    @SerializedName("legacy") val legacy: String
)

data class GameIndex(
    @SerializedName("game_index") val gameIndex: Int,
    @SerializedName("version") val version: NameUrl
)

data class MoveDetail(
    @SerializedName("move") val move: NameUrl,
    @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>
)

data class VersionGroupDetail(
    @SerializedName("level_learned_at") val levelLearnedAt: Int,
    @SerializedName("move_learn_method") val moveLearnMethod: NameUrl,
    @SerializedName("version_group") val versionGroup: NameUrl
)

data class Sprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    // @SerializedName("other") val other: OtherSprites?,
    // @SerializedName("versions") val versions: Versions?
)

// "other" field: dream_world, home, official-artwork, showdown
data class OtherSprites(
    @SerializedName("dream_world") val dreamWorld: DreamWorld?,
    @SerializedName("home") val home: HomeSprites?,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?,
    @SerializedName("showdown") val showdown: ShowdownSprites?
)

data class DreamWorld(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?
)

data class HomeSprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class ShowdownSprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class StatDetail(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: NameUrl
)

data class TypeSlot(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: NameUrl
)