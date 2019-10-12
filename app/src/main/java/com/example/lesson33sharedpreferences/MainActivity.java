package com.example.lesson33sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.SharedPreferences.Editor;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etText;
    Button btnSave, btnLoad;

    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onStop() {
        super.onStop();
        saveText();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveText();
                break;
            case R.id.btnLoad:
                loadText();
                break;
            default:
                break;
        }
    }

    @SuppressLint("ShowToast")
    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        makeText(this, "Loaded", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ShowToast")
    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor editor = sPref.edit();
        editor.putString(SAVED_TEXT, etText.getText().toString());
        editor.apply();
        makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }
}
