package com.example.a16046562.firebasehelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // Declare variable
    private TextView tvMessage;

    // TODO: Task 1 - Declare Firebase variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference rootDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI element
        tvMessage = (TextView)findViewById(R.id.textViewMessage);

        // TODO: Task 2 - Get Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootDatabaseReference = firebaseDatabase.getReference();

        // TODO: Task 3 - Add a value event listener to the "message" node
        rootDatabaseReference.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot

                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                String text = dataSnapshot.getValue(String.class);
                tvMessage.setText(text);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });
    }
}
