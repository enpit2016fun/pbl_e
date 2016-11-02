package pble.enpit2016.zerocontact.parts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import pble.enpit2016.zerocontact.R;

/**
 * Created by kyokn on 2016/10/31.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(50, 50, 50, 50);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_white_24dp,
            R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_account_circle_white_24dp,
    };
}