package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class notice_board extends AppCompatActivity {

    private static final int pic_img_req=1;
    private Spinner spnr;
    private Button slt,add;
    private ImageView img;
    private EditText des;
    private StorageReference storref;
    private DatabaseReference databaseref;
    private StorageTask mupload;
    private Uri imguri;
    private String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        spnr=findViewById(R.id.spinner_for_notice_board);
        slt=findViewById(R.id.select_image_for_notice_board);
        add=findViewById(R.id.add_new_notice);
        img=findViewById(R.id.image_for_notice_board);
        des=findViewById(R.id.add_description_for_notice_board);
        options=new String[]{"News","Events"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, options);
        spnr.setAdapter(adapter);


        slt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_image();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_notice();
            }
        });

    }

    private void upload_notice() {
        if(imguri!=null){
            storref= FirebaseStorage.getInstance().getReference("Notice").child(spnr.getSelectedItem().toString());
            databaseref= FirebaseDatabase.getInstance().getReference("Notice").child(spnr.getSelectedItem().toString());
            StorageReference imgref=storref.child(System.currentTimeMillis()+"."+getFileExtension(imguri));
            mupload=imgref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    uploadimg upimg=new uploadimg(des.getText().toString().trim(),uri.toString());
                                    String upid=databaseref.push().getKey();
                                    databaseref.child(upid).setValue(upimg);
                                    Toast.makeText(notice_board.this,"Upload Successful",Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(notice_board.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(this,"No image is selected",Toast.LENGTH_SHORT).show();
        }
    }

    private void choose_image() {
        Intent chimg=new Intent();
        chimg.setType("image/*");
        chimg.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(chimg,pic_img_req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==pic_img_req && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(uri));
    }
}