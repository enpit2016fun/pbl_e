package pble.enpit2016.zerocontact.parts;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pble.enpit2016.zerocontact.R;

/**
 * Created by kyokn on 2016/11/22.
 */

public class UserView extends CardView {

    public UserView(Context context) {
        super(context);
    }

    public UserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UserView(Context context, String name, String hobby, int imageResource, int size) {
        super(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.parts_user_view, this);

        ImageView iconImage = (ImageView) layout.findViewById(R.id.image_user);
        iconImage.setMaxWidth(size);
        iconImage.setImageResource(imageResource);

        LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.content_user);
        linearLayout.setMinimumWidth(size);

        TextView nameText = (TextView) layout.findViewById(R.id.text_name);
        nameText.setText(name);
    }

}
