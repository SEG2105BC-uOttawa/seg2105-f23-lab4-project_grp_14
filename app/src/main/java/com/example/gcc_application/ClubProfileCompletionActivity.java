package com.example.gcc_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ClubProfileCompletionActivity extends AppCompatActivity {

    // Declare UI components
    private EditText instagramLinkEditText;
    private EditText mainContactNameEditText;
    private EditText phoneNumberEditText;
    private EditText addressEditText;

    // Database helper instance
    private DatabaseHelper databaseHelper;

    // Other variables
    private String clubId; // Assuming clubId is declared and initialized somewhere

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_profile_completion);

        // Initialize UI components
        instagramLinkEditText = findViewById(R.id.instagramLinkEditText);
        mainContactNameEditText = findViewById(R.id.mainContactNameEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        addressEditText = findViewById(R.id.addressEditText);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);
    }

    private void updateClubProfile() {
        String instagramLink = instagramLinkEditText.getText().toString().trim();
        String mainContactName = mainContactNameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(instagramLink) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(address)) {
            showToast("Please fill all mandatory fields.");
            return;
        }

        // Additional validation for phone number and other fields
        if (!isValidPhoneNumber(phoneNumber)) {
            showToast("Invalid phone number.");
            return;
        }

        // Perform other validations as needed
        if (databaseHelper.updateClubProfile(clubId, instagramLink, mainContactName, phoneNumber, address)) {
            showToast("Club profile updated successfully.");
        } else {
            showToast("Error updating club profile.");
        }
    }

    private void associateEvents() {
        List<String> selectedEvents = getSelectedEvents(); // Implement this based on your UI
        if (selectedEvents.isEmpty()) {
            showToast("Select at least one event.");
            return;
        }

        // Associate selected events with the club
        if (databaseHelper.associateEventsWithClub(clubId, selectedEvents)) {
            showToast("Events associated successfully.");
        } else {
            showToast("Error associating events.");
        }
    }

    private void deleteEvent() {
        // Implement logic to get the event ID to delete
        String eventId = ""; // Replace with actual implementation

        // Delete the event from the club's profile
        if (databaseHelper.deleteEventFromClub(clubId, eventId)) {
            showToast("Event deleted successfully.");
        } else {
            showToast("Error deleting event.");
        }
    }

    private void processServiceRequest() {
        // Implement logic to get the service request ID and approval status
        String requestId = ""; // Replace with actual implementation
        boolean isApproved = true; // Replace with actual implementation

        // Update the status of the service request
        if (databaseHelper.updateServiceRequestStatus(requestId, isApproved)) {
            showToast(isApproved ? "Service request approved." : "Service request rejected.");
        } else {
            showToast("Error processing service request.");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return Patterns.PHONE.matcher(phoneNumber).matches();
    }

    private List<String> getSelectedEvents() {
        // Implement method to get selected events from the UI
        // Return a list of event IDs or names
        // This depends on how events are represented in your app
        // For example, you might have a list of checkboxes or a multi-selection spinner
        // and you would iterate through the UI elements to determine the selected events
        // and return them as a List<String>.
        return null; // Placeholder, replace with actual implementation
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
