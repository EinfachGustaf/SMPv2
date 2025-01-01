package live.einfachgustaf.mods.smp.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object AnySerializer : KSerializer<Any> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Any")

    override fun serialize(encoder: Encoder, value: Any) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw IllegalStateException("This class can be saved only by Json")
        val jsonElement = when (value) {
            is Int -> JsonPrimitive(value)
            is String -> JsonPrimitive(value)
            is Boolean -> JsonPrimitive(value)
            is Double -> JsonPrimitive(value)
            is Float -> JsonPrimitive(value)
            is Long -> JsonPrimitive(value)
            is JsonElement -> value
            is Map<*, *> -> JsonObject(value.mapKeys { it.key.toString() }.mapValues { it.value as JsonElement })
            is List<*> -> JsonArray(value.map { it as JsonElement })
            else -> throw IllegalArgumentException("Unsupported type: ${value::class}")
        }
        jsonEncoder.encodeJsonElement(jsonElement)
    }

    override fun deserialize(decoder: Decoder): Any {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw IllegalStateException("This class can be loaded only by Json")
        val jsonElement = jsonDecoder.decodeJsonElement()
        return when {
            jsonElement is JsonPrimitive && jsonElement.isString -> jsonElement.content
            jsonElement is JsonPrimitive && jsonElement.intOrNull != null -> jsonElement.int
            jsonElement is JsonPrimitive && jsonElement.booleanOrNull != null -> jsonElement.boolean
            jsonElement is JsonPrimitive && jsonElement.doubleOrNull != null -> jsonElement.double
            jsonElement is JsonPrimitive && jsonElement.floatOrNull != null -> jsonElement.float
            jsonElement is JsonPrimitive && jsonElement.longOrNull != null -> jsonElement.long
            jsonElement is JsonObject -> jsonElement
            jsonElement is JsonArray -> jsonElement


            else -> throw IllegalArgumentException("Unsupported type: ${jsonElement::class}")
        }
    }
}