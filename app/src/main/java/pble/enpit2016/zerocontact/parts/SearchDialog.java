package pble.enpit2016.zerocontact.parts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by kyokn on 2016/12/02.
 */

public class SearchDialog extends AlertDialog.Builder {

    private Context context;

    private DialogInterface.OnClickListener listener;

    private ArrayList<String> checkedItemsList = new ArrayList<>();

    public SearchDialog(Context context) {
        super(context);
        this.context = context;
    }

    public void showDialog(DialogInterface.OnClickListener listener) {
        this.listener = listener;
        init(context);
    }

    private TextView createTitleTextView(Context context) {
        TextView text = new TextView(context);
        int padding = 50;
        text.setBackgroundColor(Color.parseColor("#4784BF"));
        text.setTextColor(Color.WHITE);
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        text.setPadding(padding, padding, padding, padding);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        return text;
    }

    private void init(final Context context) {
        final String[] items1 = {"趣味", "出会った場所", "出身"};
        final String[] items2 = {"スポーツ", "読書", "映画鑑賞", "音楽", "アニメ", "プログラミング"};
        final String[] items3 = {"学会", "授業", "スポーツ観戦", "合コン"};
        final String[] items4 = {"北海道", "東北", "関東", "関西", "中国・四国", "九州", "沖縄"};
        final ArrayList<Integer> checkedItems = new ArrayList<>();


        // タイトル部分のTextView
        TextView title = createTitleTextView(context);
        title.setText("詳細検索");

        // タイトル部分のTextView
        final TextView hobbyTitle = createTitleTextView(context);
        hobbyTitle.setText("趣味");

        // タイトル部分のTextView
        final TextView placementTitle = createTitleTextView(context);
        placementTitle.setText("出会った場所");

        // タイトル部分のTextView
        final TextView hometownTitle = createTitleTextView(context);
        hometownTitle.setText("出身");

        setCustomTitle(title);

        setItems(items1, new DialogInterface.OnClickListener() {

            //親dialogのitemsが選択されたら呼び出されるメソッド
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // item_which pressed
                if (Objects.equals(items1[which], items1[0])) {
                    childDialog(hobbyTitle, context, items2, checkedItems);
                } else if (Objects.equals(items1[which], items1[1])) {
                    childDialog(placementTitle, context, items3, checkedItems);
                } else if (Objects.equals(items1[which], items1[2])) {
                    childDialog(hometownTitle, context, items4, checkedItems);
                }
            }
        });

        setNegativeButton("キャンセル", null);
        show();
    }

    private void childDialog(TextView text, Context context, final String[] items, final ArrayList<Integer> checkedItems) {
        new AlertDialog.Builder(context)
                .setCustomTitle(text)
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) checkedItems.add(which);
                        else checkedItems.remove((Integer) which);
                        checkedItemsList.add(items[which]);
                    }
                })
                .setPositiveButton("検索", listener)
                .setNegativeButton("キャンセル", null)
                .show();
    }

    public void clearCheckedItemsList() {
        checkedItemsList.clear();
    }

    public ArrayList<String> getCheckedItemsList() {
        return checkedItemsList;
    }


}
