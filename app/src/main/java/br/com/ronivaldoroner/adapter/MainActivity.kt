package br.com.ronivaldoroner.adapter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var txtFooter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listView.emptyView = findViewById(android.R.id.empty)

        val adapter = VehicleAdapter(this, vehicles)
        listView.adapter = adapter
        initHeaderAndFooter(listView, adapter)

        listView.setOnItemClickListener { parent, _, position, _ ->
            val vehicle = parent.getItemAtPosition(position) as? Vehicle

            if (vehicle != null) {
                val (model, year) = vehicles[position - 1]
                Toast.makeText(this, "$model - $year", Toast.LENGTH_LONG).show()
                vehicles.remove(vehicle)
                adapter.notifyDataSetChanged()
                txtFooter.text = resources.getQuantityString(R.plurals.footer_text, adapter.count, adapter.count)

            }
        }

    }

    private fun initHeaderAndFooter(listView: ListView, adapter: VehicleAdapter) {
        val padding = 8
        val txtHeader = TextView(this)
        txtHeader.setBackgroundColor(Color.GRAY)
        txtHeader.setTextColor(Color.WHITE)
        txtHeader.setText(R.string.header_text)
        txtHeader.setPadding(padding, padding, 0, padding)
        listView.addHeaderView(txtHeader)

        txtFooter = TextView(this)
        txtFooter.text = resources.getQuantityString(R.plurals.footer_text, adapter.count, adapter.count)
        txtFooter.setBackgroundColor(Color.LTGRAY)
        txtFooter.gravity = Gravity.END
        txtFooter.setPadding(0, padding, padding, padding)
        listView.addFooterView(txtFooter)
    }

    private val vehicles = mutableListOf(
        Vehicle("ka", 2019, 3, true, true),
        Vehicle("Onix", 2018, 1, true, true),
        Vehicle("Cobolt", 2017, 1, true, true),
        Vehicle("Del Rey", 1988, 3, false, true),
        Vehicle("Golf", 2018, 0, true, true),
        Vehicle("Cruze", 2017, 1, true, true),
        Vehicle("Uno", 2007, 2, true, false),
        Vehicle("Fiesta", 2017, 3, true, true),
        Vehicle("Gol", 2014, 0, true, true),
        Vehicle("Parati", 2010, 0, true, false)
    )
}
