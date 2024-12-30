package live.einfachgustaf.mods.smp.discord.whitelist.models

/**
 * Data class representing a beta key
 *
 * @property key The key
 * @property claimedBy The user who claimed the key
 * @property claimedAt The time the key was claimed
 * @property expiresAt The time the key expires
 */
data class BetaKey(
    val key: String,
    val claimedBy: String? = null, // String because of MongoDB serialization
    val claimedAt: Long? = null,
    val expiresAt: Long? = null
) {
    /**
     * Whether the key is claimed
     */
    val isClaimed: Boolean
        get() = claimedBy != null

    /**
     * Whether the key is expired
     */
    val isExpired: Boolean
        get() = expiresAt != null && System.currentTimeMillis() > expiresAt
}