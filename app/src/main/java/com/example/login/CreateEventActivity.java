package com.example.login;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;



    ImageView eventImage;
    EditText eventTitle;
    EditText eventDescription;
    EditText eventLocation;
    EditText eventDate;
    Button uploadButton;
    Button addEvent;
    ProgressBar progressBar;

    private Uri imageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);


        eventImage = findViewById(R.id.event_Image);
        eventTitle = findViewById(R.id.event_title);
        eventDescription = findViewById(R.id.event_description);
        eventLocation = findViewById(R.id.event_location);
        eventDate = findViewById(R.id.event_date);
        uploadButton = findViewById(R.id.uploadImageButton);
        addEvent = findViewById(R.id.create_event);
        progressBar = findViewById(R.id.progressbar);

        mStorageRef = FirebaseStorage.getInstance().getReference("EventData" );
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("EventData");



        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();

            }
        });






    }

    private void openFileChooser(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
           && data.getData() !=null){
            imageUri =data.getData();

            Picasso.get().load(imageUri).into(eventImage);
        }
    }

    private void createEvent(){

        String event_title = eventTitle.getText().toString().trim();
        String event_desc = eventDescription.getText().toString().trim();
        String event_loc = eventLocation.getText().toString().trim();
        String event_date = eventDate.getText().toString().trim();

        if(event_title.isEmpty()){
            eventTitle.setError("Event Title is required ");
            eventTitle.requestFocus();
            return;
        }

        if(event_desc.isEmpty()){
            eventDescription.setError("Event Description is required ");
            eventDescription.requestFocus();
            return;
        }

        if(event_loc.isEmpty()){
            eventLocation.setError("Event Location is required ");
            eventLocation.requestFocus();
            return;
        }

        if(event_date.isEmpty()){
            eventDate.setError("Event Date is required ");
            eventDate.requestFocus();
            return;
        }





    }
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime =  MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }
    private void uploadFile(){
        if(eventImage != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() +
                    "." + getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(CreateEventActivity.this , "Event added", Toast.LENGTH_LONG).show();
                         //  Event event = new Event(eventTitle.getText().toString().trim(),
                           //        taskSnapshot.getDownloadUrl().toString());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(CreateEventActivity.this , e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.VISIBLE);

                        }
                    });


        }
        else {
            Toast.makeText(this , "You must select an image " , Toast.LENGTH_LONG).show();

        }

    }
}
