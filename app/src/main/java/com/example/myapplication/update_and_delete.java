package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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


public class update_and_delete extends AppCompatActivity {
private static final int pic_img_req=1;
private Button chbutn,upbtn;
private TextView shwups;
private EditText fnam;
private ImageView simg;
private ProgressBar uppgr;
private StorageReference storref;
private DatabaseReference databaseref;
private StorageTask mupload;
private String nm;

private Uri imguri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);
        setTitle("Upload Image");
        chbutn=findViewById(R.id.choose_image);
        upbtn=findViewById(R.id.imgup);
        fnam=findViewById(R.id.imgfilename);
        simg=findViewById(R.id.upimg);
        uppgr=findViewById(R.id.prgimg);

        Bundle bdn=getIntent().getExtras();
        nm= bdn.getString("name");

        storref= FirebaseStorage.getInstance().getReference(nm);
        databaseref= FirebaseDatabase.getInstance().getReference(nm);

        chbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             chooseimage();
            }
        });
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(mupload!=null && mupload.isInProgress())
               {
                   Toast.makeText(update_and_delete.this,"One Upload is on Progress",Toast.LENGTH_SHORT).show();
               }
               else
               {
                uploadimage();
               }
            }
        });

    }
    private void chooseimage(){
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
            simg.setImageURI(imguri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadimage(){
        if(imguri!=null){
            StorageReference imgref=storref.child(System.currentTimeMillis()+"."+getFileExtension(imguri));
            mupload=imgref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imgref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                            uploadimg upimg=new uploadimg(fnam.getText().toString().trim(),uri.toString());
                            String upid=databaseref.push().getKey();
                            databaseref.child(upid).setValue(upimg);
                            Toast.makeText(update_and_delete.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                            uppgr.setProgress(0);
                            }
                        });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        Toast.makeText(update_and_delete.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            uppgr.setProgress((int) progress);
                        }
                    });
        }
        else{
            Toast.makeText(this,"No file selected",Toast.LENGTH_SHORT).show();
        }
    }
}