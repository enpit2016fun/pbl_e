package pble.enpit2016.zerocontact.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.parts.Icon;
/**
 * Created by yamada-PC on 2016/12/02.
 */

public class UserListViewAdapter extends ArrayAdapter<Icon>{
    private LayoutInflater inflater;

    public UserListViewAdapter(Context context, int resource, List<Icon> objects){
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Icon icon = getItem(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_row, null);
        }

        TextView id = (TextView) convertView.findViewById(R.id.id_view);
        id.setText(icon.getName());

        TextView name = (TextView) convertView.findViewById(R.id.name_view);
        name.setText(icon.getHobby());

        return convertView;
    }
}
