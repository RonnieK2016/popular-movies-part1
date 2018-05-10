package com.example.android.popularmoviespart1.listeners;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by angelov on 5/8/2018.
 */
public abstract class MoviesRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int currentPage = 1;
    private GridLayoutManager layoutManager;
    private boolean isLoading = false;
    private int previousTotal = 0;
    private int numberOfVisibleItems = 5;

    public MoviesRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        numberOfVisibleItems = numberOfVisibleItems * layoutManager.getSpanCount();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!isLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItemPosition + numberOfVisibleItems)) {
            loadMoreItems(++currentPage);
            isLoading = true;
        }

    }

    public void resetState() {
        isLoading = false;
        previousTotal = 0;
        currentPage = 1;
    }

    protected abstract void loadMoreItems(int currentPage);
}

