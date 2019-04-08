package com.freeportmetrics.kotlin_internal_project.view

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeportmetrics.kotlin_internal_project.R
import com.freeportmetrics.kotlin_internal_project.di.*
import com.freeportmetrics.kotlin_internal_project.helper.*
import com.freeportmetrics.kotlin_internal_project.model.Event
import com.freeportmetrics.kotlin_internal_project.model.Flight
import com.freeportmetrics.kotlin_internal_project.model.Rocket
import com.freeportmetrics.kotlin_internal_project.viewmodel.BaseViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.EventViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.FlightViewModel
import com.freeportmetrics.kotlin_internal_project.viewmodel.RocketViewModel
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private enum class DataType(val id: Int) {
        NO_TYPE(-1),
        ROCKETS(0),
        NEXT_FLIGHTS(1),
        PAST_EVENTS(2),
    }

    private val boxStore: BoxStore by inject()
    private val darkModeHelper: DarkModeHelper by inject()
    private val rocketVm: RocketViewModel by viewModel()
    private val flightVm: FlightViewModel by viewModel()
    private val eventVm: EventViewModel by viewModel()

    private lateinit var sharedPref: SharedPreferences
    private var darkMode = false
    private var currentDataType = DataType.NO_TYPE.id

    //region Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(listOf(repositoryModule, networkModule, boxModule, viewModelModule, helperModule))
        }

        sharedPref = getPreferences(Context.MODE_PRIVATE)
        darkMode = sharedPref.getBoolean("darkMode", false)
        currentDataType = sharedPref.getInt("currentDataType", DataType.NO_TYPE.id)

        rv_generic?.layoutManager = LinearLayoutManager(this)
        rv_generic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        setListeners()
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            configureAutoDarkMode()
        } else {
            darkModeHelper.onModeChanged(darkMode, delegate)
        }
        handleDataReloading()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
    //endregion

    //region Dark Mode
    private fun configureAutoDarkMode() {
        val currentNightMode = resources.configuration.uiMode - 1
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_YES -> darkMode = true
            Configuration.UI_MODE_NIGHT_NO -> darkMode = false
        }
    }

    private fun handleDataReloading() {
        when (currentDataType) {
            DataType.ROCKETS.id -> populateAdapter(boxStore.boxFor<Rocket>().all)
            DataType.NEXT_FLIGHTS.id -> populateAdapter(boxStore.boxFor<Flight>().all)
            DataType.PAST_EVENTS.id -> populateAdapter(boxStore.boxFor<Event>().all)
        }
    }
    //endregion

    //region Data loading
    private fun setListeners() {
        val rocketBox = boxStore.boxFor<Rocket>()
        val flightBox = boxStore.boxFor<Flight>()
        val eventBox = boxStore.boxFor<Event>()

        btn_rockets.setOnClickListener {
            currentDataType = DataType.ROCKETS.id
            if (rocketBox.all == null || rocketBox.all.isEmpty()) {
                downloadAndSaveData(rocketVm)
            } else {
                populateAdapter(rocketBox.all)
            }
        }

        btn_next_flights.setOnClickListener {
            currentDataType = DataType.NEXT_FLIGHTS.id
            if (flightBox.all == null || flightBox.all.isEmpty()) {
                downloadAndSaveData(flightVm)
            } else {
                populateAdapter(flightBox.all)
            }
        }

        btn_events.setOnClickListener {
            currentDataType = DataType.PAST_EVENTS.id
            if (eventBox.all == null || eventBox.all.isEmpty()) {
                downloadAndSaveData(eventVm)
            } else {
                populateAdapter(eventBox.all)
            }
        }

        swipe_refresh.setOnRefreshListener {
            when (currentDataType) {
                DataType.ROCKETS.id -> downloadAndSaveData(rocketVm)
                DataType.NEXT_FLIGHTS.id -> downloadAndSaveData(flightVm)
                DataType.PAST_EVENTS.id -> downloadAndSaveData(eventVm)
            }
        }
    }

    private fun <T> downloadAndSaveData(viewModel: BaseViewModel<T>) {
        val liveData = viewModel.getDataFromRetrofit()
        liveData.observe(this, Observer { data ->
            if (data != null) {
                populateAdapter(data)
                viewModel.saveToDatabase(data)
                swipe_refresh.isRefreshing = false
            }
        })
    }

    private fun <T> populateAdapter(data: List<T>) {
        rv_generic.adapter = GenericAdapter(this@MainActivity, data)
    }
    //endregion

    //region Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.switch_modes_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        darkModeHelper.handleIconChange(menu, darkMode)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.mnu_set_theme -> {
                darkMode = !darkMode
                sharedPref.edit {
                    putBoolean("darkMode", darkMode)
                    putInt("currentDataType", currentDataType)
                }
                darkModeHelper.onModeChanged(darkMode, delegate)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //endregion
}
