package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titileInp=findViewById(R.id.titleInp);
        EditText descriptionInp=findViewById(R.id.DescriptionInp);
        MaterialButton saveBtn=findViewById(R.id.saveBtn);

        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=titileInp.getText().toString();
                String description=descriptionInp.getText().toString();
                long createdTim=System.currentTimeMillis();

                realm.beginTransaction();
                Notes notes= (Notes) realm.createObject(Notes.class);
                notes.setTitle(title);
                notes.setDescription(description);
                notes.setCreatedTim(createdTim);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note Saved",Toast.LENGTH_LONG).show();
                finish();

            }
        });

    }
}