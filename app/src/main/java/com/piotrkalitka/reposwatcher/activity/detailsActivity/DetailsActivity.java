package com.piotrkalitka.reposwatcher.activity.detailsActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.piotrkalitka.reposwatcher.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtAuthor)
    TextView txtAuthor;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    Unbinder unbinder;

    private DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        unbinder = ButterKnife.bind(this);
        presenter = new DetailsPresenter(this);

        presenter.bindData(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void bindData(String name, String username, String description, String avatar) {
        txtName.setText(name);
        txtAuthor.setText(username);
        txtDescription.setText(description);

        Picasso
                .get()
                .load(avatar)
                .into(imgAvatar);
    }
}
