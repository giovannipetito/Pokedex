package com.satispay.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    // @SerializedName("base_happiness") val baseHappiness: Int,
    // @SerializedName("capture_rate") val captureRate: Int,
    // @SerializedName("color") val color: Color,
    // @SerializedName("egg_groups") val eggGroups: List<EggGroup>,
    // @SerializedName("evolution_chain") val evolutionChain: EvolutionChain,
    // @SerializedName("evolves_from_species") val evolvesFromSpecies: Any?,  // Could also make a small class if needed
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    // @SerializedName("form_descriptions") val formDescriptions: List<Any>,
    // @SerializedName("forms_switchable") val formsSwitchable: Boolean,
    // @SerializedName("gender_rate") val genderRate: Int,
    // @SerializedName("genera") val genera: List<Genus>,
    // @SerializedName("generation") val generation: NamedAPIResource,
    // @SerializedName("growth_rate") val growthRate: NamedAPIResource,
    // @SerializedName("habitat") val habitat: NamedAPIResource?,
    // @SerializedName("has_gender_differences") val hasGenderDifferences: Boolean,
    // @SerializedName("hatch_counter") val hatchCounter: Int,
    // @SerializedName("id") val id: Int,
    // @SerializedName("is_baby") val isBaby: Boolean,
    // @SerializedName("is_legendary") val isLegendary: Boolean,
    // @SerializedName("is_mythical") val isMythical: Boolean,
    // @SerializedName("name") val name: String,
    // @SerializedName("names") val names: List<Name>,
    // @SerializedName("order") val order: Int,
    // @SerializedName("pal_park_encounters") val palParkEncounters: List<PalParkEncounter>,
    // @SerializedName("pokedex_numbers") val pokedexNumbers: List<PokedexNumber>,
    // @SerializedName("shape") val shape: NamedAPIResource?,
    // @SerializedName("varieties") val varieties: List<Variety>
) {
    data class Color(
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String
    )

    data class EggGroup(
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String
    )

    data class EvolutionChain(
        @SerializedName("url") val url: String
    )

    data class FlavorTextEntry(
        @SerializedName("flavor_text") val flavorText: String,
        @SerializedName("language") val language: NamedAPIResource,
        @SerializedName("version") val version: NamedAPIResource
    )

    data class Genus(
        @SerializedName("genus") val genus: String,
        @SerializedName("language") val language: NamedAPIResource
    )

    data class Name(
        @SerializedName("language") val language: NamedAPIResource,
        @SerializedName("name") val name: String
    )

    data class PalParkEncounter(
        @SerializedName("area") val area: NamedAPIResource,
        @SerializedName("base_score") val baseScore: Int,
        @SerializedName("rate") val rate: Int
    )

    data class PokedexNumber(
        @SerializedName("entry_number") val entryNumber: Int,
        @SerializedName("pokedex") val pokedex: NamedAPIResource
    )

    data class Variety(
        @SerializedName("is_default") val isDefault: Boolean,
        @SerializedName("pokemon") val pokemon: NamedAPIResource
    )

    /**
     * A generic reusable data class that captures
     * a resource with a name and a URL (common in the PokéAPI).
     */
    data class NamedAPIResource(
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String
    )
}