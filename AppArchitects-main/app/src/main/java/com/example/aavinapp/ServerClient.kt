package com.example.aavinapp

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException


fun requestTransitions(userSession: SessionDetails): List<Transaction> {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://localhost:8080/profile/transactions")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        val responseData = response.body?.string()
        val listType = object : TypeToken<List<Transaction>>() {}.type
        return Gson().fromJson(responseData, listType)
    }
}

fun requestTransaction(userSession: SessionDetails): Transaction {
    println("Here Da")
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://localhost:8080/profile/transaction")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        val responseData = response.body?.string()
        val listType = object : TypeToken<Transaction>() {}.type
        return Gson().fromJson(responseData, listType)
    }
}
class ServerClient (userSession: SessionDetails) {
    var user: SessionDetails = userSession
    val base_url = "http://localhost:8080"
    fun requestLogin(): String? {
        var json = Json.encodeToString(this.user)
        var res: Response = createPost("$base_url/login", json)
        return res.body?.string()
    }

    fun requestSignUp(): String? {
        var json = Json.encodeToString(this.user)
        var res: Response = createPost("$base_url/signup", json)
        return res.body?.string()
    }

    fun requestRoute(route: String): Response{
        var json = Json.encodeToString(this.user)
        var res: Response = createPost(base_url + route, json)
        return res
    }
}

fun main() {
    println("Running")
    println(
        requestTransaction(
            SessionDetails(
            "", "", "", null, null
        )
        )
    )
    println("Running")
}
fun createPost(url: String, json: String): Response {
    // Create OkHttpClient instance
    val client = OkHttpClient()
    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    // Define the request body
    val requestBody: RequestBody = json.toRequestBody(JSON)

    // Create the request object
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    // Execute the request
    val response: Response = client.newCall(request).execute()

    return response
}
