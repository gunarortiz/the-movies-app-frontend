package com.example.ieee.helpers

import android.content.Context
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by EyvindTC on 4/25/18.
 **/

class BaseInterceptor : Interceptor {
    private val PATH_ASSETS_TEST = ""
    private var message = ""
    private var segmentPath: String? = ""
    private var context: Context? = null
    private var code: Int = 0

    companion object {
        var timeRequest: Long = 0
    }

    constructor(context: Context, fileDir: String, path: String, isArr: Boolean = false) {
        this.context = context
        this.message = getJson(context, fileDir, isArr).toString()
        this.segmentPath = path
        this.code = 200
    }

    constructor(context: Context) {
        this.context = context
        this.message = "{\"success\":true}"
        this.segmentPath = null
        this.code = 200
    }

    constructor(context: Context, fileDir: String, segmentPath: String, code: Int) {
        this.context = context
        this.message = getJson(context, fileDir, true).toString()
        this.segmentPath = segmentPath
        this.code = code
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val pathSegments = request.url().pathSegments()
        val path = StringBuilder()//PreferencesHelper.getServerUrl(context);
        for (i in pathSegments.indices) {//[api, categories] , start in index 1
            path.append(pathSegments[i])
            if (i + 1 < pathSegments.size) path.append("/")
        }

        if (timeRequest > 0) {
            try {
                Thread.sleep(timeRequest)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        val response: Response
        if (segmentPath == null || path.toString() == segmentPath) {
            println("**********************Request inject to: " + request.url().encodedPath())
            response = Response.Builder()
                .code(code)
                .message(message)
                .addHeader("content-type", "application/json")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), message.toByteArray()))
                .build()
        } else {
            response = chain.proceed(request)
        }
        return response
    }

    private fun getJson(context: Context, fileDir: String, isArr: Boolean): Any {
        try {
            var fullPath = "$PATH_ASSETS_TEST/$fileDir"
            if (fullPath.startsWith("/")) {
                fullPath = fullPath.replaceFirst("/", "")
            }
            println("Path Json: $fullPath")
            val `is` = context.assets.open(fullPath)
            return if (isArr) {
                JSONArray(readInputStream(`is`))
            } else {
                JSONObject(readInputStream(`is`))
            }
        } catch (ignored: Exception) {
            ignored.printStackTrace()
            return JSONObject()
        }

    }

    private fun readInputStream(inputStream: InputStream): String {
        val sb = StringBuilder()
        try {
            val br = BufferedReader(InputStreamReader(inputStream))
            var read: String? = br.readLine()
            while (read != null) {
                sb.append(read)
                read = br.readLine()
            }
            br.close()
        } catch (ignored: Exception) {

        }

        return sb.toString()
    }
}