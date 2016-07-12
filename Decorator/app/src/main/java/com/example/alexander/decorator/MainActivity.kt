package com.example.alexander.decorator


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    // названия телефонов (элементов)
    var coffeeGroup: MutableList<String>  = mutableListOf("Латте", "Каппучино", "Кофе")
    var teaGroup: MutableList<String>  = mutableListOf("Чай")
    var milkshakeGroup: MutableList<String>  = mutableListOf("Молочный коктейль")
    // коллекция для групп
    var groupData: ArrayList<Map<String, String>>? = null
    // коллекция для элементов одной группы
    var childDataItem: ArrayList<Map<String, String>>? = null
    var childData: ArrayList<ArrayList<Map<String, String>>>? = null
    // в итоге получится childData = ArrayList<childDataItem>
    // список аттрибутов группы или элемента
    var m: Map<String, String>? = null
    var elvMain: ExpandableListView? = null

    var groups: MutableList<String>  = mutableListOf("КОФЕ", "ЧАЙ", "МОЛОЧНЫЙ КОКТЕЙЛЬ")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonBegin.setOnClickListener {
            setContentView(R.layout.expandeblelist)
            // заполняем коллекцию групп из массива с названиями групп
            val groupData = ArrayList<Map<String, String>>()
            for (group in groups) {
                // заполняем список аттрибутов для каждой группы
                val m = HashMap<String, String>()
                m.put("groupName", group) // имя компании
                groupData.add(m)
            }
            elvMain.toString()
            // список аттрибутов групп для чтения
            var groupFrom = arrayOf("groupName")
            // список ID view-элементов, в которые будет помещены аттрибуты групп
            var groupTo = intArrayOf(android.R.id.text1)


            // создаем коллекцию для коллекций элементов
            var childData = ArrayList<ArrayList<Map<String, String>>>()

            // создаем коллекцию элементов для первой группы
            var childDataItem = ArrayList<Map<String, String>>()
            // заполняем список аттрибутов для каждого элемента
            for (beverage in coffeeGroup) {
                val m = HashMap<String, String>()
                m.put("beverageName", beverage) // название телефона
                childDataItem.add(m)
            }
            // добавляем в коллекцию коллекций
            childData.add(childDataItem)

            // создаем коллекцию элементов для второй группы
            childDataItem = ArrayList<Map<String, String>>()
            for (beverage in teaGroup) {
                var m = HashMap<String, String>()
                m.put("beverageName", beverage)
                childDataItem.add(m)
            }
            childData.add(childDataItem)

            // создаем коллекцию элементов для третьей группы
            childDataItem = ArrayList<Map<String, String>>()
            for (beverage in milkshakeGroup) {
                var m = HashMap<String, String>()
                m.put("beverageName", beverage)
                childDataItem.add(m)
            }
            childData.add(childDataItem)

            // список аттрибутов элементов для чтения
            val childFrom = arrayOf("beverageName")
            // список ID view-элементов, в которые будет помещены аттрибуты элементов
            val childTo = intArrayOf(android.R.id.text1)

            val adapter = SimpleExpandableListAdapter(
                    this,
                    groupData,
                    android.R.layout.simple_expandable_list_item_1,
                    groupFrom,
                    groupTo,
                    childData,
                    android.R.layout.simple_list_item_1,
                    childFrom,
                    childTo)

            var elvMain = findViewById(R.id.elvMain) as ExpandableListView
            elvMain.setAdapter(adapter)

        }

        }
}