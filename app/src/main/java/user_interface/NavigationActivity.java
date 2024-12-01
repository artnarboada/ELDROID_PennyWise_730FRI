package user_interface;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.eldroid.pennywise.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class NavigationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DashboardFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Log.d("Navigation Activity", "Selected Menu ID: " + id);
                Fragment selectedFragment;

                int itemId = menuItem.getItemId();
                if (itemId == R.id.navigator_dashboard) {
                    selectedFragment = new DashboardFragment();
                } else if (itemId == R.id.navigator_profile) {
                    selectedFragment = new ProfileFragment();
                } else if (itemId == R.id.navigator_settings) {
                    selectedFragment = new SettingsFragment();
                } else {
                    throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                );
                transaction.replace(R.id.fragment_container, selectedFragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            }
        });
}

}