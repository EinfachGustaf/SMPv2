package live.einfachgustaf.mods.smp.discord.whitelist

import com.mongodb.client.model.Filters
import kotlinx.coroutines.flow.firstOrNull
import live.einfachgustaf.mods.smp.data.db.MongoDB
import live.einfachgustaf.mods.smp.discord.whitelist.models.BetaKey

/**
 * Singleton providing database utilities for the whitelist feature
 */
internal object DiscordWhitelist {

    /**
     * Collection of beta keys
     */
    private val betaKeyCollection = MongoDB.database.getCollection<BetaKey>("betakeys")

    /**
     * Retrieves a beta key by its key
     *
     * @param key The key to retrieve
     * @return The beta key, or null if it does not exist or the database operation fails
     */
    suspend fun createKey(key: String, expiresAt: Long? = null): BetaKey? {
        val document = BetaKey(key, expiresAt = expiresAt)
        val insert = betaKeyCollection.insertOne(document)

        // check if the insert was successful
        if (insert.wasAcknowledged()) {
            return document
        }
        return null
    }

    /**
     * Creates many beta keys
     *
     * @param keys The keys to create
     * @param expiresAt The time the keys expire
     * @return The list of beta keys created
     */
    suspend fun createManyKeys(keys: List<String>, expiresAt: Long? = null): List<BetaKey> {
        val documents = keys.map { BetaKey(it, expiresAt = expiresAt) }
        val insert = betaKeyCollection.insertMany(documents)

        // check if the insert was successful
        if (insert.wasAcknowledged()) {
            return documents
        }
        return emptyList()
    }

    /**
     * Creates many beta keys
     *
     * @param amount The amount of keys to create
     * @param size The size of the keys
     * @param expiresAt The time the keys expire
     * @return The list of beta keys created
     */
    suspend fun createManyKeys(amount: Int, expiresAt: Long? = null): List<BetaKey> {
        val keys = (1..amount).map { generateRandomKey() }
        return createManyKeys(keys, expiresAt)
    }

    /**
     * Retrieves a beta key by its key
     *
     * @param key The key to retrieve
     * @return The beta key, or null if it does not exist or the database operation fails
     */
    suspend fun getKey(key: String): BetaKey? {
        return betaKeyCollection.find(Filters.eq("key", key)).firstOrNull()
    }

    /**
     * Deletes a beta key by its key
     *
     * @param key The key to delete
     * @return True if the key was deleted, false otherwise
     */
    // TODO: check if key exists before deleting
    suspend fun deleteKey(key: String): Boolean {
        val delete = betaKeyCollection.deleteOne(Filters.eq("key", key))
        return delete.wasAcknowledged()
    }

    suspend fun updateKey(key: String, expiresAt: Long): Boolean {
        val update = betaKeyCollection.updateOne(Filters.eq("key", key), org.bson.Document("\$set", org.bson.Document("expiresAt", expiresAt)))
        return update.wasAcknowledged()
    }

    suspend fun isWhitelisted(user: String): Boolean {
        return betaKeyCollection.find(Filters.eq("claimedBy", user)).firstOrNull() != null
    }

    /**
     * Upserts a beta key by its key
     *
     * @param key The key to upsert
     * @param betaKey The beta key to upsert
     */
    // TODO: fix upsert - currently replaces the BetaKey if it exists instead of updating it
    suspend fun upsert(key: String, betaKey: BetaKey) {
        val existingKey = betaKeyCollection.find(Filters.eq("key", key)).firstOrNull()
        if (existingKey == null) {
            betaKeyCollection.insertOne(betaKey)
            println("Inserted new key: $key")
        } else {
            betaKeyCollection.replaceOne(Filters.eq("key", key), betaKey)
            println("Replaced existing key: $key")
        }
    }

    /**
     * Generates a random key
     *
     * @param size The size of the key
     * @return The generated key
     */
    fun generateRandomKey(size: Int = 16): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..size)
            .map { chars.random() }
            .joinToString("")
    }
}