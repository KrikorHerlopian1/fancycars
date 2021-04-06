package com.krikorherlopian.fancycars.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.krikorherlopian.fancycars.R

abstract class SingleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        fragment?.let {
        } ?: run {
            fragment = createFragment()
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                fragment!!
            ).commit()
        }
    }

    protected abstract fun createFragment(): Fragment?
}
