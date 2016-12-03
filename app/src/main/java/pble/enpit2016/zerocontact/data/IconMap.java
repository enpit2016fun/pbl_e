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
        put("2", new Icon("工藤大希", "プログラミング", "ひえええお化けだああ", R.drawable.user_sample_1));
        put("7", new Icon("本田翼", "アニメ", "アオハライド見てね", R.drawable.user_sample_2));
        put("5", new Icon("清原", "映画鑑賞", "おいのび太、野球しようぜ", R.drawable.user_sample_3));
        put("6", new Icon("佐々木優太", "プログラミング", "エロは世界を救う", R.drawable.user_sample_4));
    }

}
