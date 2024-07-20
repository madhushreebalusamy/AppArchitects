package com.example.aavinapp

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Transaction (
    var transactionID: String?,
    var farmerID: String,
    var dateOfTransaction: String,
    var quantity: Float,
    var quality: Float,
    var societyID: Int,
    var rate: Float
)


@Keep
@Serializable
data class SessionDetails(
    var farmerID: String,
    var sessionID: String,
    var deviceDetail: String,
    var password: String?,
    var transactionID: String?,
)
