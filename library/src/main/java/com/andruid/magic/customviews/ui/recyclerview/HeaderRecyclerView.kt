package com.andruid.magic.customviews.ui.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.getResourceIdOrThrow
import androidx.recyclerview.widget.RecyclerView
import com.andruid.magic.customviews.R
import com.andruid.magic.eezetensions.hide
import com.andruid.magic.eezetensions.show

class HeaderRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private lateinit var headerView: View

    private var headerViewID: Int

    private val emptyObserver = object : AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            toggleVisibility()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            toggleVisibility()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            toggleVisibility()
        }

        override fun onChanged() {
            super.onChanged()
            toggleVisibility()
        }
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.HeaderRecyclerView, defStyleAttr, 0
        )
        headerViewID = a.getResourceIdOrThrow(R.styleable.HeaderRecyclerView_headerView)
        a.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        headerView = (parent as View).findViewById(headerViewID)
        emptyObserver.onChanged()
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.let {
            it.registerAdapterDataObserver(emptyObserver)
            emptyObserver.onChanged()
        }
    }

    private fun toggleVisibility() {
        if (!::headerView.isInitialized)
            return
        if (adapter?.itemCount == 0)
            headerView.hide()
        else
            headerView.show()
    }
}