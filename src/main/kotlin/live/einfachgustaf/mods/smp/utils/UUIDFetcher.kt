package live.einfachgustaf.mods.smp.utils

import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Data class representing a player UUID response
 * @property id The UUID of the player
 * @property name The name of the player
 */
@Serializable
data class PlayerUUIDResponse(val id: String, val name: String)

/**
 * Utility class for fetching player UUIDs
 * @property client The HTTP client to use for requests
 */
class UUIDFetcher(private val client: OkHttpClient = OkHttpClient()) {

    /**
     * Fetches a player UUID by their name
     * @param playerName The name of the player
     * @param splitUuid Whether to split the UUID into a hyphenated format
     * @return The UUID of the player, or null if the player does not exist
     */
    fun fetchUUID(playerName: String, splitUuid: Boolean = true): String? {
        val request = Request.Builder()
            .url("https://api.mojang.com/users/profiles/minecraft/$playerName")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null

            response.body?.string()?.let { responseBody ->
                val playerUUIDResponse = json.decodeFromString<PlayerUUIDResponse>(responseBody)
                val uuid = playerUUIDResponse.id
                return if (splitUuid) {
                    "${uuid.substring(0, 8)}-${uuid.substring(8, 12)}-${uuid.substring(12, 16)}-${uuid.substring(16, 20)}-${uuid.substring(20)}"
                } else {
                    uuid
                }
            }
        }

        return null
    }
}