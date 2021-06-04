package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



//import com.amplifyframework.datastore.AWSDataStorePlugin;
//import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.taskmaster.Databases.AppDataBase;
import com.example.taskmaster.Models.State;
import com.example.taskmaster.Models.Task;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class AddTaskActivity extends AppCompatActivity {
    private Spinner spinner;
    private static final String[] paths = {"new", "assigned", "in progress","complete"};
    private int selectedItem;
    private String fileName;
    private String key = "";
    private boolean is_img = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button button =  findViewById(R.id.addTask_add);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTaskActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    selectedItem =  item.toString() == "new" ? 0: item.toString() == "assigned" ? 1 : item.toString() == "in progress" ? 2 : 3;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem = 1;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddTaskActivity.this,MainActivity.class);
                EditText titleField = (EditText)findViewById(R.id.title);
                EditText bodyField = (EditText)findViewById(R.id.body);
                if((titleField.getText() != null) && titleField.getText().toString() != "" && bodyField.getText() != null && bodyField.getText().toString() != ""){

                    AppDataBase.getAppDataBase(getApplicationContext()).taskDao().insertAll(new Task(titleField.getText().toString(),bodyField.getText().toString(), State.values()[selectedItem],key,is_img));
//                    try {
//                        Task item = Task.builder()
//                                .title(titleField.getText().toString())
//                                .body(bodyField.getText().toString())
//                                .state(State.values()[selectedItem])
//                                .build();
//                        Amplify.DataStore.save(item,
//                                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
//                                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
//                        );
//                        Log.i("Tutorial", "Initialized Amplify");
//                    } catch (Exception e) {
//                        Log.e("Tutorial", "Could not initialize Amplify", e);
//                    }
                    TextView submitted  =  findViewById(R.id.submitted);
                    submitted.setText("submitted!");
                    startActivity(intent);
                }else {
                    TextView submitted  =  findViewById(R.id.submitted);
                    submitted.setText("Fill out all required fields");
                }

            }
        });
        findViewById(R.id.upload_file).setOnClickListener(v -> {
            fileName = ((EditText)findViewById(R.id.name_of_file_to)).getText().toString();
            if(fileName != null){
                getFile();
            }else {
                TextView submitted  =  findViewById(R.id.submitted);
                submitted.setText("Fill out all required fields");
            }
        });

    }

    private void getFile(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("*/*");
        is_img = false;
        key = this.fileName;
        startActivityForResult(intent,9999);
    };
    private void uploadFile(File file) {
        Amplify.Storage.uploadFile(
                fileName,
                file,
                result -> this.key =  result.getKey(),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }

//    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9999) {
            File file = new File(getApplicationContext().getFilesDir(),"uploads");
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    String pathEnd =  filePath.substring(filePath.lastIndexOf(".")).toUpperCase();
                    System.out.println(pathEnd);
                    System.out.println(pathEnd == ".JPG");

                    if(pathEnd.equals(".JPG") || pathEnd.equals(".PNG") || pathEnd.equals(".TIFF") || pathEnd.equals(".GIF") || pathEnd.equals(".PSD") || pathEnd.equals(".PDF") || pathEnd.equals(".EPS") || pathEnd.equals(".AI") || pathEnd.equals(".INDD") || pathEnd.equals(".RAW") ){
                        is_img = true;
                    }
                }
                cursor.close();
            }
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
//                FileUtils.copy(inputStream,new FileOutputStream(file));
                try {
                    OutputStream out = new FileOutputStream(file);
                    try {
                        // Transfer bytes from in to out
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        uploadFile(file);
                    } finally {
                        out.close();
                    }
                }finally {
                    inputStream.close();
                }
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTaskActivity that = (AddTaskActivity) o;
        return selectedItem == that.selectedItem &&
                is_img == that.is_img &&
                Objects.equals(spinner, that.spinner) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spinner, selectedItem, fileName, key, is_img);
    }
}