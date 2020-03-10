package com.andruid.magic.customviews.ui.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.getResourceIdOrThrow
import androidx.recyclerview.widget.RecyclerView
import com.andruid.magic.customviews.R
import com.andruid.magic.eezetensions.hide
import com.andruid.magic.eezetensions.show

/**
 * RecyclerView to show a empty view when no item is present
 */
open class EmptyRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    // empty view
    private var emptyView: View? = null

    // empty view ID
    private var emptyViewID: Int

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
                attrs, R.styleable.EmptyRecyclerView, defStyleAttr, 0
        )
        emptyViewID = a.getResourceIdOrThrow(R.styleable.EmptyRecyclerView_emptyView)
        a.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        emptyView = rootView.findViewById(emptyViewID)
        emptyObserver.onChanged()
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.let {
            it.registerAdapterDataObserver(emptyObserver)
            emptyObserver.onChanged()
        }
    }

    /**
     * Hide empty layout if adapter is not empty and show recyclerView else do vice versa
     */
    private fun toggleVisibility() {
        if (adapter?.itemCount == 0) {
            emptyView?.show()
            hide()
        } else {
            emptyView?.hide()
            show()
        }
    }
}