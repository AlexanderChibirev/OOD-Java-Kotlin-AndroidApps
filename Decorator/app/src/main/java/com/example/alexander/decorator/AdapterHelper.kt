package com.example.alexander.decorator

import android.content.Context
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import java.util.*

/**
 * Created by Alexander on 11.08.2016.
 */
class AdapterHelper(_ctx: Context) {
    private val ATTR_BEVERAGE_NAME = "beverageName"
    private var groups: MutableList<String>  = mutableListOf("КОФЕ", "ЧАЙ", "МОЛОЧНЫЙ КОКТЕЙЛЬ")
    private var coffeeGroup: MutableList<String>  = mutableListOf("Латте", "Каппучино", "Кофе")
    private var teaGroup: MutableList<String>  = mutableListOf("Чай")
    private var milkshakeGroup: MutableList<String>  = mutableListOf("Молочный коктейль")
    private var m: Map<String, String>? = null
    private var elvMain: ExpandableListView? = null
    private var ctx: Context? = _ctx
    private var adapter: SimpleExpandableListAdapter? = null

    fun getAdapter(): SimpleExpandableListAdapter? {

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

        adapter = SimpleExpandableListAdapter(
                ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo)
        return adapter
    }

    fun getChildText(groupPos: Int, childPos: Int): String? {
        return (adapter?.getChild(groupPos, childPos) as Map<String, String>)[ATTR_BEVERAGE_NAME]
    }

    fun getGroupChildText(groupPos: Int, childPos: Int): String? {
        return getChildText(groupPos, childPos)
    }
}
