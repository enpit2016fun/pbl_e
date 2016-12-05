package pble.enpit2016.zerocontact.samples.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.data.IconMap;
import pble.enpit2016.zerocontact.parts.Icon;

/**
 * Created by kyokn on 2016/11/25.
 */

public class ListViewFragment extends Fragment {

    private UserListViewAdapter adapter;

    private IconMap iMap = new IconMap();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.samples_fragment_listview, container, false);
        adapter = new UserListViewAdapter(getActivity(), 0, new ArrayList<Icon>());
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(adapter);

        setListView();
        return view;
    }

    public void setListView() {
        List<Icon> list = new ArrayList<>();
        for (Map.Entry<String, Icon> entry : iMap.entrySet()) {
            list.add(new Icon(entry.getValue().getName(), entry.getValue().getHobby(), entry.getValue().getComment(), entry.getValue().getImage()));
        }
        adapter.addAll(list);
    }

}
