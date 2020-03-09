package com.andruid.magic.customviewsample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import com.andruid.magic.customviewsample.R
import com.andruid.magic.customviewsample.databinding.ActivityMainBinding
import com.andruid.magic.customviewsample.ui.adapter.StringItemAdapter
import com.andruid.magic.eezetensions.drawable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val stringItemAdapter = StringItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()

        binding.fab.setOnClickListener {
            if (stringItemAdapter.itemCount == 0) {
                stringItemAdapter.submitList(listOf("item1", "item2", "item3", "item4")) {
                    binding.fab.setImageDrawable(drawable(R.drawable.ic_invisible))
                }
            } else {
                stringItemAdapter.submitList(emptyList<String>()) {
                    binding.fab.setImageDrawable(drawable(R.drawable.ic_visible))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = stringItemAdapter
        }
    }
}