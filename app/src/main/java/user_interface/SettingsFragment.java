package user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.pennywise.R;

import user_authentication.LoginActivity;
import user_authentication.Register;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.activity_settings, container, false);

        // Find the ImageView for navigation to the home screen
        ImageView toHome = view.findViewById(R.id.settings);
        toHome.setOnClickListener(v -> {
            // Navigate to the DashboardFragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment()) // Replace with your fragment container ID
                    .addToBackStack(null)
                    .commit();
        });

        // Find the Logout TextView and set the OnClickListener
        TextView logoutTextView = view.findViewById(R.id.logout);
        logoutTextView.setOnClickListener(v -> logout());

        return view;
    }

    private void logout() {
        // Clear any saved user data (e.g., shared preferences)
        // Example of clearing SharedPreferences
        getActivity().getSharedPreferences("user_pref", getContext().MODE_PRIVATE).edit().clear().apply();

        // Navigate back to the Register screen (or Login screen)
        Intent intent = new Intent(getActivity(), LoginActivity.class); // Use getActivity() to get the context
        startActivity(intent);
        getActivity().finish(); // Close this activity to prevent the user from going back to the settings screen
    }
}
