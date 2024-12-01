package user_interface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.pennywise.R;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.activity_settings, container, false);

        // Find the ImageView for navigation
        ImageView toHome = view.findViewById(R.id.settings);
        toHome.setOnClickListener(v -> {
            // Navigate to the DashboardFragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment()) // Replace with your fragment container ID
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
