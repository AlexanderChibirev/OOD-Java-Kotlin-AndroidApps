package com.example.alexander.recycleviewwithdownloadphotos

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import java.util.*
/**
 * Created by Alexander on 22.08.2016.
 */

class DataAdapter(private val context: Context, private val android_versions: ArrayList<PhotoData>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view)
    }//// тут создаем холдер

    //Обработка ImageViewрециркуляции и загрузки в гашение адаптера.
    //Сложные преобразования изображения с минимальным использованием памяти.
    //Автоматическая память и кэширование диска.
   override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        ///////////////////////////////////////////////////////
        val path = "sdcard/photoalbum/downloaded_image${i}.png"
        try {
            val ifStream = FileInputStream(path)
        }
        catch (e: FileNotFoundException)
        {
            val downloadTask = DownloadTask(i)
            downloadTask.execute(android_versions[i].androidImageUrl)
        }
  //      viewHolder.tv_android.setTextSize(20.0f)
 //       viewHolder.tv_android.setText(android_versions[i].androidVersionName)

        try {
            viewHolder.img_android.setImageDrawable(Drawable.createFromPath(path))
        }
        catch (e:OutOfMemoryError)
        {
            val toast = Toast.makeText(context,
                    "Ошибка памяти", Toast.LENGTH_SHORT).
                    show()
        }
       // тут создадим класс Donalder
     /*   Picasso.with(context).load(android_versions[i].androidImageUrl)
                .error(R.drawable.placeholder).resize(300, 200)
                .placeholder(R.drawable.placeholder)
                .resize(300, 200)
                .into(viewHolder.img_android)*/
       //Реализовал решение на базе Picasso, а не ручками, загружая, масштабируя и кешируя картинки.
    }


    override fun getItemCount(): Int {
        return android_versions.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tv_android: TextView
        internal var img_android: ImageView

        init {

            tv_android = view.findViewById(R.id.person_name) as TextView
            img_android = view.findViewById(R.id.person_photo) as ImageView
        }
    }




    internal inner class DownloadTask(idPhoto: Int) : AsyncTask<String, Int, String>() {

        private var m_idPhoto = idPhoto

        override fun doInBackground(vararg params: String): String {
            val path = params[0]
            var file_length = 0
            try {
                val url = URL(path)
                val urlConnection = url.openConnection()
                urlConnection.connect()
                file_length = urlConnection.contentLength
                val new_folder = File("sdcard/photoalbum")
                if (!new_folder.exists()) {
                    new_folder.mkdir()
                }
                val input_file = File(new_folder, "downloaded_image${m_idPhoto}.png") //название фото
                val inputStream = BufferedInputStream(url.openStream(), 8192)
                val data = ByteArray(1024*2)
                var total = 0
                var count = 0
                val outputStream = FileOutputStream(input_file)
                while ((count) != -1) {//чтоб фотка загрузилась
                    total += count
                    outputStream.write(data, 0, count)
                    val progress = total.toInt() * 100 / file_length
                    publishProgress(progress)
                    count = inputStream.read(data)
                }
                inputStream.close()
                outputStream.close()

            } catch (e: MalformedURLException) {
                e.message
            } catch (e: IOException) {
                e.message
            }
            return "Download Complete"
        }
    }
}