package pble.enpit2016.zerocontact;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;

        import pble.enpit2016.zerocontact.fragment.FavoriteFragment;

public class FavoriteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_favorite);

        if (savedInstanceState == null) {
            FavoriteFragment fragment = new FavoriteFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment).commit();
        }

    }
    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.text_seach, fragment);
        transaction.commit();
    }
}