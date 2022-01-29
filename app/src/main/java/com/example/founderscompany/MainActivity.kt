package com.example.founderscompany

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.founderscompany.databinding.ActivityMainBinding
import com.example.founderscompany.utils.ArrayAdapterCompany
import com.example.founderscompany.utils.ListItem
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var listView : ListView
    private lateinit var array : Array<String>
    private lateinit var arrayCountry : Array<String>
    private lateinit var listItem : ArrayList<ListItem>
    private lateinit var listItemClass : ListItem
    private lateinit var adapter : ArrayAdapterCompany
    private lateinit var toolbar : Toolbar
    private var index : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.setTitle(R.string.app_name)
        listView = findViewById(R.id.list_view)

//        array = resources.getStringArray(R.array.techno_sector)
//        arrayCountry = resources.getStringArray(R.array.techno_country)
//        for (el in array.indices) {
//
//            listItemClass = ListItem()
//            listItemClass.nameCompany = array[el]
//            listItemClass.country = arrayCountry[el]
//            listItemClass.imageId = arrTechImage[el]
//
//            listItem.add(listItemClass)
//        }

        listItem = ArrayList()
        adapter = ArrayAdapterCompany(this, R.layout.list_view_item, listItem, layoutInflater)
        listView.adapter = adapter


        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, TextContentActivity::class.java)
            intent.putExtra("category", index)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.techno -> {
                sectorBusiness(R.string.techno_sector, resources.getStringArray(R.array.techno_sector), resources.getStringArray(R.array.techno_country), getImageId(R.array.imageTechCompany), 0)
            }
            R.id.car -> {
                sectorBusiness(R.string.car_sector, resources.getStringArray(R.array.car_sector), resources.getStringArray(R.array.car_country), getImageId(R.array.imageCarCompany), 1)
            }
            R.id.bank -> {
                sectorBusiness(R.string.bank_sector, resources.getStringArray(R.array.bank_sector), resources.getStringArray(R.array.bank_country), getImageId(R.array.imageBankCompany), 2)
            }
            R.id.oil_gas -> {
                sectorBusiness(R.string.oil_gas_sector, resources.getStringArray(R.array.oil_gas_sector), resources.getStringArray(R.array.oil_gas_country), getImageId(R.array.imageOilGasCompany), 3)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun sectorBusiness(title: Int, nameArr : Array<String>, nameCountry : Array<String>, imageCompany : IntArray, categoryIndex : Int) {
        toolbar.setTitle(title)
        array = resources.getStringArray(R.array.techno_sector)
        arrayCountry = resources.getStringArray(R.array.techno_country)
        adapter.clear()
        for (el in nameArr.indices) {

            listItemClass = ListItem()
            listItemClass.nameCompany = nameArr[el]
            listItemClass.country = nameCountry[el]
            listItemClass.imageId = imageCompany[el]

            listItem.add(listItemClass)
        }
        index = categoryIndex
    }

    private fun getImageId(imageId : Int) : IntArray {
        val tArray : TypedArray = resources.obtainTypedArray(imageId)
        val count = tArray.length()
        val ids = IntArray(count)

        for (el in ids.indices) {
            ids[el] = tArray.getResourceId(el, 0)
        }
        tArray.recycle()
        return ids
    }
}