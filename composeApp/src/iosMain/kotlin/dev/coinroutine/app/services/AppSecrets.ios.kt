package dev.coinroutine.app.services

import dev.coinroutine.app.utils.getStringResource

actual object AppSecrets {
    actual val coinrankingApiKey = getStringResource(
        "Secrets",
        "plist",
        "coinrankingApiKey"
    ) ?: ""
}