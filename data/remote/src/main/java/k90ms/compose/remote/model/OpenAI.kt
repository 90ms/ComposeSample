package k90ms.compose.remote.model

import com.google.gson.annotations.SerializedName

class OpenAI {

    data class RQ(
        @SerializedName("prompt")
        val prompt: String,
        @SerializedName("temperature")
        val temperature: Int? = 0,
        @SerializedName("max_tokens")
        val maxTokens: Int? = 4000,
        @SerializedName("model")
        val model: String? = "text-davinci-003"
    )

    data class RS(
        val choices: List<Payload>
    ) {
        data class Payload(
            @SerializedName("text")
            val text: String
        )
    }
}