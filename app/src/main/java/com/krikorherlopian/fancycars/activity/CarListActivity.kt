package com.krikorherlopian.fancycars.activity;

import androidx.fragment.app.Fragment
import com.krikorherlopian.fancycars.R
import com.krikorherlopian.fancycars.fragment.CarListFragment
import kotlinx.android.synthetic.main.activity_fragment.*

class CarListActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return CarListFragment();
    }
}