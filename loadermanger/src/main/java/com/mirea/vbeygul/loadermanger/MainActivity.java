package com.mirea.vbeygul.loadermanger;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234;
    public TextView text;
    public EditText word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, "mirea");
        getSupportLoaderManager().initLoader(LoaderID, bundle, this);

        word = findViewById(R.id.changeText);
        text = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
    }

    public void onClick(View view) {
        change(word.getText().toString());
    }

    private void change(String word) {
        ArrayList<Character> jumblew = new ArrayList<Character>();
        for (int i = 0; i < word.length(); i++) {
            jumblew.add(word.charAt(i));
        }
        Collections.shuffle(jumblew);
        String result = "";
        for (Character character : jumblew) {
            result += character;
        }
        text.setText(result);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + i, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, bundle);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished" + s);
            Toast.makeText(this, "onLoadFinished:" + s, Toast.LENGTH_SHORT).show();
        }
    }
}