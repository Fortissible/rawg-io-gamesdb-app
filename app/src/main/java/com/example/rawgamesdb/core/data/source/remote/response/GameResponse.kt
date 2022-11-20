package com.example.rawgamesdb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("previous")
	val previous: String,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class Platform(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class EsrbRating(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
)

data class ResultsItem(

	@field:SerializedName("added")
	val added: Int,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("metacritic")
	val metacritic: Int,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: String,

	@field:SerializedName("ratings")
	val ratings: Ratings,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("added_by_status")
	val addedByStatus: AddedByStatus,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("updated")
	val updated: String,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String
)

data class Requirements(

	@field:SerializedName("minimum")
	val minimum: String,

	@field:SerializedName("recommended")
	val recommended: String
)

data class AddedByStatus(
	val any: Any? = null
)

data class PlatformsItem(

	@field:SerializedName("requirements")
	val requirements: Requirements,

	@field:SerializedName("released_at")
	val releasedAt: String,

	@field:SerializedName("platform")
	val platform: Platform
)

data class Ratings(
	val any: Any? = null
)
