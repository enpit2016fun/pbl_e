package pble.enpit2016.zerocontact.data;

import java.util.HashMap;

import pble.enpit2016.zerocontact.R;
import pble.enpit2016.zerocontact.parts.Icon;

/**
 * Created by kyokn on 2016/11/24.
 */

public class IconMap extends HashMap<String, Icon> {

    public IconMap() {
        createSampleIcons();
    }

    private void createSampleIcons() {
        put("-4", new Icon("Daiki Kudo", "プログラミング", "ひえええお化けだああ", R.drawable.user_sample_kudo));
        put("-5", new Icon("Yuta Sasaki", "アニメ", "エロスの神ここに降臨したり", R.drawable.user_sample_sasaki));
        put("-1", new Icon("Kazuma Osanai", "スポーツ", "あの日に帰りたいな・・・", R.drawable.user_sample_osanai));
        put("4", new Icon("Takaya Yamada", "スポーツ", "「筋肉と俺」絶賛発売中！", R.drawable.user_sample_yamada));
        put("5", new Icon("Mizuki kato", "音楽", "ここは任せて先に行け～", R.drawable.user_sample_kato));
        put("6", new Icon("Yoshimitsu Kazama", "映画鑑賞", "CD出しました。", R.drawable.user_sumple_kazama));
        put("-2", new Icon("Kasumi Arimura", "読書", "とても動きが速いです", R.drawable.user_sample_arimura));
        put("-3", new Icon("KIYOHARA", "スポーツ", "おいのび太、野球しようぜ", R.drawable.user_sample_kiyohara));
    }

}
