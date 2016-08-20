@file:Suppress("DEPRECATION")

package com.example.alexander.downloadphoto

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    private var urlName: EditText? = null
    private var btnGetDataFromJsonFile: Button? = null
    private var LOG_TAG = "my_log"
    private val allPhoto = ArrayList<PhotoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        urlName = findViewById(R.id.urlName) as EditText
        btnGetDataFromJsonFile =  findViewById(R.id.getDataFromJsonFile) as Button
        btnGetDataFromJsonFile!!.setOnClickListener {
            ParseTask().execute()
            goToPhotoViewerActivity()
        }
    }
   private inner class ParseTask : AsyncTask<Void, Void, String>() {

        internal var urlConnection: HttpURLConnection? = null
        internal var reader: BufferedReader? = null
        internal var resultJson = ""

        override fun doInBackground(vararg params: Void): String {
            // получаем данные с внешнего ресурса
            try {//urlName!!.getText().toString()
                val url = URL("https://raw.githubusercontent.com/AlexanderChibirev/Json/master/Photos.json")

                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection!!.requestMethod = "GET"
                urlConnection!!.connect()

                val inputStream = urlConnection!!.inputStream
                val buffer = StringBuffer()

                inputStream.reader().forEachLine {
                    buffer.append(it)
                }
                reader = BufferedReader(InputStreamReader(inputStream))
                resultJson = buffer.toString()

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return resultJson
        }

        override fun onPostExecute(strJson: String) {
            super.onPostExecute(strJson)
            // выводим целиком полученную json-строку
            //Log.d(LOG_TAG, strJson)
            var dataJsonObj: JSONObject? = null
            try {
                dataJsonObj = JSONObject(strJson)
                val photos = dataJsonObj.getJSONArray("arrayPhotos")
                for(i in 0..photos.length() - 1) {
                    val jsonPhotos = photos.getJSONObject(i)
                    val urlPhotos= jsonPhotos.getString("url")
                    val namePhotos =  jsonPhotos.getString("name")
                    allPhoto.add(PhotoData(namePhotos,urlPhotos))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    private fun goToPhotoViewerActivity() {
        val intent = Intent(this, PhotoViewerActivity::class.java)
        intent.putExtra("allPhoto", allPhoto)
        startActivity(intent)
       //var x = PhotoViewerActivity()
    }
}

