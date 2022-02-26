package com.example.founderscompany.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.founderscompany.R
import com.example.founderscompany.utils.ArrayAdapterCompany
import com.example.founderscompany.utils.ListItem
import com.google.android.material.navigation.NavigationView
const val NAME = 0
const val COUNTRY = 1
const val COMP_IMAGE = 2
const val FOUNDER_IMAGE = 3

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var listView : ListView
    private lateinit var listItem : ArrayList<ListItem>
    private lateinit var listItemClass : ListItem
    private lateinit var adapter : ArrayAdapterCompany
    private lateinit var toolbar : Toolbar
    private lateinit var array : Array<String>
    private lateinit var arraySearch : ArrayList<ListItem>
    private lateinit var btnClose : ImageButton
    private lateinit var searchView: SearchView
    private lateinit var cardView: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        closeButton()

        initIconMenu()
        initAdapter()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        initIntent()
        firstWindow()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.techno -> {
                sectorBusiness(R.string.techno_sector, resources.getStringArray(R.array.techno_sector),0)
            }
            R.id.car -> {
                sectorBusiness(R.string.car_sector, resources.getStringArray(R.array.car_sector),1)
            }
            R.id.bank -> {
                sectorBusiness(R.string.bank_sector, resources.getStringArray(R.array.bank_sector),2)
            }
            R.id.oil_gas -> {
                sectorBusiness(R.string.oil_gas_sector, resources.getStringArray(R.array.oil_gas_sector), 3)
            }
            R.id.info_app -> {
                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search_main) cardView.visibility = View.VISIBLE
        return super.onOptionsItemSelected(item)
    }


    private fun sectorBusiness(title: Int, nameArr : Array<String>, categoryIndex : Int) {
        toolbar.setTitle(title)
        adapter.clear()
        for ((index, el) in nameArr.withIndex()) {

            listItemClass = ListItem()
            val tempArray = el.split("|")
            listItemClass.nameCompany = tempArray[NAME]
            listItemClass.country = tempArray[COUNTRY]
            listItemClass.imagePath = tempArray[COMP_IMAGE]
            listItemClass.foundImagePath = tempArray[FOUNDER_IMAGE]
            listItemClass.pos = index
            listItem.add(listItemClass)
        }
        arraySearch.clear()
        arraySearch.addAll(listItem)
    }

    private fun firstWindow() {
        array = resources.getStringArray(R.array.all_company)
        for (el in array) {
            listItemClass = ListItem()
            val tempArray = el.split("|")
            listItemClass.nameCompany = tempArray[NAME]
            listItemClass.country = tempArray[COUNTRY]
            listItemClass.imagePath = tempArray[COMP_IMAGE]
            listItem.add(listItemClass)
        }
    }

    private fun initIconMenu() {
        val toggle = ActionBarDrawerToggle(this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initIntent() {
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, TextContentActivity::class.java)
            intent.putExtra("image", adapter.getItem(position)?.foundImagePath)
            intent.putExtra("position", adapter.getItem(position)?.pos)
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        listItem = ArrayList()
        arraySearch = ArrayList()
        adapter = ArrayAdapterCompany(this, R.layout.list_view_item, listItem, layoutInflater)
        listView.adapter = adapter
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        btnClose = findViewById(R.id.btn_close_search)
        searchView = findViewById(R.id.search_view)
        cardView = findViewById(R.id.card_search)
        searchView.setOnQueryTextListener(this)

        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
        drawerLayout = findViewById(R.id.drawer_layout)
    }

    private fun closeButton() {
        listView = findViewById(R.id.list_view)
        btnClose.setOnClickListener {
            cardView.visibility = View.GONE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        getSearchResult(newText)
        return true
    }

    private fun getSearchResult(text : String?) {
        if (text == null) return
        val tempList = ArrayList<ListItem>()
        arraySearch.forEach {
            if (it.nameCompany.lowercase().startsWith(text.lowercase()) || text.isEmpty())
                tempList.add(it)
        }
        adapter.clear()
        adapter.addAll(tempList)
        adapter.notifyDataSetChanged()
    }
}