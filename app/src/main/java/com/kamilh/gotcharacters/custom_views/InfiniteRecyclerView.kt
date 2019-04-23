package com.kamilh.gotcharacters.custom_views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var loading = false

    fun init(layoutManager: LinearLayoutManager, onBottomReached: () -> Unit) {
        setLayoutManager(layoutManager)
        setHasFixedSize(true)
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (!loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            onBottomReached()
                        }
                    }
                }
            }
        })
    }
}
