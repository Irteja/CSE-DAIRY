package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class ADD_USER extends AppCompatActivity {
    private static final int pic_img_req = 1;
    private EditText roll,reg,session,phone,name,peraddress,prsaddress;
    private Button adusr,slect_img;
    private String[] select_new_user;
    private DatabaseReference databaseuser;
    private Uri imguri;
    private ImageView simg;
    private StorageReference storref;
    private StorageTask mupload;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Add student Information");
        name=findViewById(R.id.student_name);
        roll=findViewById(R.id.student_roll);
        reg=findViewById(R.id.student_reg);
        session=findViewById(R.id.student_session);
        phone=findViewById(R.id.student_phone_number);
        adusr=findViewById(R.id.add_new_user);
        peraddress=findViewById(R.id.student_permanent_address);
        prsaddress=findViewById(R.id.student_present_address);
        slect_img=findViewById(R.id.select_student_image);
        simg=findViewById(R.id.student_image);

        slect_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_img();
            }
        });

        adusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadalldata();
            }
        });

    }

    private void choose_img() {
        Intent stulimg = new Intent();
        stulimg.setType("image/*");
        stulimg.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(stulimg, pic_img_req);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_img_req && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            simg.setImageURI(imguri);
        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver scr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(scr.getType(uri));
    }



    private void uploadalldata() {
        String first=roll.getText().toString();
        String second=reg.getText().toString();
        String thrid=session.getText().toString();
        String fourth=phone.getText().toString();
        String five=name.getText().toString();
        String six=peraddress.getText().toString();
        String seven=prsaddress.getText().toString();
        if(TextUtils.isEmpty(first) || TextUtils.isEmpty(second) || TextUtils.isEmpty(thrid) || TextUtils.isEmpty(fourth) || TextUtils.isEmpty(five) || TextUtils.isEmpty(six) || TextUtils.isEmpty(seven) ){
            Toast.makeText(ADD_USER.this,   "Fill both the text fields", Toast.LENGTH_SHORT).show();
        }
        else if(imguri==null){
            Toast.makeText(ADD_USER.this,   "Please Select a Image for the Student", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseuser= FirebaseDatabase.getInstance().getReference("Contacts").child("Student");
            storref= FirebaseStorage.getInstance().getReference("Contacts").child("Student");
//          storing_user_info str=new storing_user_info(first,second,thrid,fourth,five);



            StorageReference imgref = storref.child(System.currentTimeMillis() + "." + getFileExtension(imguri));
            mupload = imgref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    student_info str=new student_info(five,first,second,thrid,fourth,six,seven,uri.toString());
                                    databaseuser.push().setValue(str);
                                    Toast.makeText(ADD_USER.this, "Successfully added a student", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ADD_USER.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

}