package gr.dasoftware.flickrapp.Classes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import gr.dasoftware.flickrapp.R;

public class FlickrImageViewHolder extends RecyclerView.ViewHolder {

    public ImageView thumbnail;
    public TextView title;

    public FlickrImageViewHolder(View view) {
        super(view);

        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        title = (TextView) view.findViewById(R.id.title);
    }
}
