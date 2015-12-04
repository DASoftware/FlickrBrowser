package gr.dasoftware.flickrapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import gr.dasoftware.flickrapp.R;

public class BaseActivity extends AppCompatActivity {

    final String FLICKR_QUERY = "FLICKR_QUERY";
    private Toolbar mToolbar;

    protected Toolbar activateToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }

        return mToolbar;
    }

    protected Toolbar activateToolBatWithHomeEnabled() {
        activateToolbar();
        if (mToolbar != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return mToolbar;
    }
}
