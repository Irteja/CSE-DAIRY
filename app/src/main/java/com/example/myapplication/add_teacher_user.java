package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.core.Tag;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class add_teacher_user extends AppCompatActivity {

    private static final int pic_img_req = 1;

    private EditText teacher_nm, teacher_dsg, teacher_phn;
    private Spinner spnr;
    private String[] slt;
    private Button add_teacher_button, select_btn;
    private ImageView timg;
    private Uri imguri;
    private StorageReference storref;
    private DatabaseReference databaseref;
    private StorageTask mupload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_user);
        setTitle("Add a new Teacher");
        teacher_nm = findViewById(R.id.teacher_name);
        teacher_dsg = findViewById(R.id.teacher_designation);
        teacher_phn = findViewById(R.id.teacher_phone);
        spnr = findViewById(R.id.dept_head_or_other);
        add_teacher_button = findViewById(R.id.teacher_user_add_button);
        select_btn = findViewById(R.id.select_timage);
        timg = findViewById(R.id.teacher_image);

        slt = new String[]{"Teacher", "Head of the department"};
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, slt);
        spnr.setAdapter(spinner_adapter);

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseimage();
            }
        });

        add_teacher_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addteacher();
            }

        });
    }


    private void chooseimage() {
        Intent slimg = new Intent();
        slimg.setType("image/*");
        slimg.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(slimg, pic_img_req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_img_req && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            timg.setImageURI(imguri);
        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void addteacher() {
        String first = teacher_nm.getText().toString();
        String second = teacher_dsg.getText().toString();
        String third = teacher_phn.getText().toString();
        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(second) || TextUtils.isEmpty(third))
            Toast.makeText(this, "Please fill all the text field ", Toast.LENGTH_SHORT).show();
        else if (imguri != null) {
            String s = spnr.getSelectedItem().toString();
            if (s.equals("Head of the department")) {
                databaseref = FirebaseDatabase.getInstance().getReference("Contacts").child("Teacher").child("head");
                databaseref.removeValue();
                storref = FirebaseStorage.getInstance().getReference("Contacts").child("Teacher").child("head");

                storref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item:listResult.getItems()){
                            item.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(add_teacher_user.this,"deleting",Toast.LENGTH_SHORT).show();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(add_teacher_user.this,"something went wrong",Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(add_teacher_user.this,"something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        });



                databaseref = FirebaseDatabase.getInstance().getReference("Contacts").child("Teacher").child("head");
                storref = FirebaseStorage.getInstance().getReference("Contacts").child("Teacher").child("head");

            } else {
                storref = FirebaseStorage.getInstance().getReference("Contacts").child("Teacher").child("others");
                databaseref = FirebaseDatabase.getInstance().getReference("Contacts").child("Teacher").child("others");

            }



            StorageReference imgref = storref.child(System.currentTimeMillis() + "." + getFileExtension(imguri));
            mupload = imgref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    storing_user_info str = new storing_user_info(first, second, third, uri.toString());
                                    databaseref.push().setValue(str);
                                    Toast.makeText(add_teacher_user.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(add_teacher_user.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        }
                    });


        }

    }
}