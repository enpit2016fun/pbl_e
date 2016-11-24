package pble.enpit2016.zerocontact.parts;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pble.enpit2016.zerocontact.R;

/**
 * Created by kyokn on 2016/11/23.
 */

public class UserDetailView extends CardView {
    public UserDetailView(Context context) {
        super(context);
    }

    public UserDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UserDetailView(Context context, String name, String hobby, String comment, int imageResource) {
        super(context);

        View layout = LayoutInflater.from(context).inflate(R.layout.parts_user_detail_view, this);

        ImageView iconImage = (ImageView) layout.findViewById(R.id.image_user);
        iconImage.setImageResource(imageResource);

        TextView nameText = (TextView) layout.findViewById(R.id.text_name);
        nameText.setText(name);

        TextView hobbyText = (TextView) layout.findViewById(R.id.text_hobby);
        hobbyText.setText(hobby);

        TextView commentText = (TextView) layout.findViewById(R.id.user_comment);
        commentText.setText(comment);

        Button button = (Button) layout.findViewById(R.id.user_detail_button);
        String buttonText = "さんをもっと知る";
        button.setText(name + buttonText);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
