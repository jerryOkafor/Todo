package me.jerryhanks.todo.core

import android.content.Context
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/

object RestServiceTestHelper {
    const val CORE_ANDROID_TEST_ASSET_BASE_PATH = "../core/src/test/assets/"

    @Throws(Exception::class)
    fun convertStreamToString(stream: InputStream?): String {
        val reader = BufferedReader(InputStreamReader(stream))
        val sb = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }

    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        val stream: InputStream = context.resources.assets.open(filePath)
        val ret = convertStreamToString(stream)
        //Make sure you close all streams.
        stream.close()
        return ret
    }

    @Throws(Exception::class)
    fun getStringFromFile(filePath: String): String {
        val reader =
            BufferedReader(InputStreamReader(FileInputStream(CORE_ANDROID_TEST_ASSET_BASE_PATH + filePath)))
        val sb = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            sb.append(line).append("\n")
        }
        reader.close()
        return sb.toString()
    }
}