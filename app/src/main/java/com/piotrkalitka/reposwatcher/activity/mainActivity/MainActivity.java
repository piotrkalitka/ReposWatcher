package com.piotrkalitka.reposwatcher.activity.mainActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.piotrkalitka.reposwatcher.R;
import com.piotrkalitka.reposwatcher.api.model.RepoItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private MenuItem btnSortAlphabetical;

    @BindView(R.id.reposList)
    RecyclerView reposList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presenter = new MainPresenter(this);

        initReposList();
        initSwipeRefreshLayout();
        presenter.getData(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        btnSortAlphabetical = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort:
                presenter.sortReposList();
                break;
        }
        return false;
    }


    @Override
    public void hideSwipeRefreshLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setTitle(R.string.loading_error_title)
                .setMessage(R.string.loading_error_message)
                .setPositiveButton(R.string.loading_error_positive_btn, (dialogInterface, i) -> presenter.getData(true))
                .setNegativeButton(R.string.loading_error_negative_btn, (dialogInterface, i) -> finish());
        builder.show();
    }

    @Override
    public void updateReposList(List<RepoItem> items) {
        if (reposList.getAdapter() == null) {
            ReposListAdapter adapter = new ReposListAdapter(this, items);
            reposList.setAdapter(adapter);
        } else {
            ReposListAdapter reposListAdapter = (ReposListAdapter) reposList.getAdapter();
            reposListAdapter.setItems(items);
        }
    }

    @Override
    public void setSortButtonChecked(boolean state) {
        int color = ContextCompat.getColor(this, state ? R.color.sort_btn_state_checked : R.color.sort_btn_state_unchecked);
        btnSortAlphabetical.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }


    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getData(false);
        });
    }

    private void initReposList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reposList.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        reposList.addItemDecoration(dividerItemDecoration);
    }
}
