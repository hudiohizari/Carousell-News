package com.hizari.data.network.util

import android.content.Context
import com.hizari.data.R
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

abstract class SafeApiRequest {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    suspend fun <T: Any> apiRequest(
        call: suspend () -> Response<T>
    ): T? {
        val response = call.invoke()
        return checkForError(response)
    }

    private fun <T> checkForError(response: Response<T>): T? {
        if (response.isSuccessful) {
            return response.body()
        } else {
            val body = response.errorBody()?.string()
            val code = response.code().toString()
            var apiCode = ""
            var message = ""

            if (code.toInt() >= 500) {
                message = context.getString(R.string.server_down)
            } else {
                body?.let {
                    try {
                        val errors = JSONObject(it).getJSONArray("errors")
                        val error = errors.getJSONObject(0)
                        apiCode = error.getString("code")
                        message = error.getString("message")
                    } catch (e: JSONException) {
                        message = try {
                            it
                        } catch (e: Exception) {
                            "Error: ${response.message()}"
                        }
                    }
                }
            }

            throw ApiException(
                if (apiCode != "") apiCode else code,
                message
            )
        }
    }

}