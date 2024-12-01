package user_interface;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eldroid.pennywise.R;

public class ProfileFragment extends AppCompatActivity {

    private ImageView profilePicture;
    private TextView profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.profile_picture);
        profileName = findViewById(R.id.profile_name);

        findViewById(R.id.edit_icon).setOnClickListener(v -> showAvatarDialog());

        findViewById(R.id.edit_profile_button).setOnClickListener(v -> showEditNameDialog());
    }

    private void showAvatarDialog() {
        int[] avatars = {
                R.drawable.avatar_a,
                R.drawable.avatar_b,
                R.drawable.avatar_c
        };

        String[] avatarNames = {"Avatar A", "Avatar B", "Avatar C"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Avatar");

        builder.setItems(avatarNames, (dialog, which) -> {
            profilePicture.setImageResource(avatars[which]); // Change avatar
            Toast.makeText(this, avatarNames[which] + " selected!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void showEditNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Name");

        final EditText input = new EditText(this);
        input.setText(profileName.getText().toString()); // Pre-fill current name
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                profileName.setText(newName); // Update name
                Toast.makeText(this, "Name updated!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Name cannot be empty.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
