package com.example.cryptoworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cryptoworld.ui.CryptoListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var TAG = "list"
    private var handler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        handler = Handler()
        loadFragment(TAG)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.list -> {
                TAG = "list"
                loadFragment(TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.news -> {
                TAG = "news"
                loadFragment(TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.alerts -> {
                TAG = "alerts"
                loadFragment(TAG)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(type : String) {
        val fragment  = when (type) {
            "list" -> CryptoListFragment.newInstance()
            "news" -> CryptoListFragment.newInstance()
            "alerts" -> CryptoListFragment.newInstance()
            else ->  CryptoListFragment.newInstance()
        }
        handler?.post {
            try {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, fragment as Fragment)
                fragmentTransaction.commit()
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, "Error loading fragments", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
