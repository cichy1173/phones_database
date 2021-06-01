package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnPhoneCLick {

    private static final String TAG = "ERROR";
    public static final int EDIT_CODE = 99;
    public static final int SAVE_CODE = 0;

    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;
    protected FloatingActionButton fab;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);



        mAdapter = new PhoneListAdapter(this, this::onPhoneClick);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        mPhoneViewModel.getAllPhones().observe(this, elements -> {
            mAdapter.setPhoneList(elements);
        });


        fab = findViewById(R.id.fabMain);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, addToBD.class);
                startActivityForResult(intent, SAVE_CODE);
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.
                    ViewHolder target)
            {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_LONG).show();
                mPhoneViewModel.deletePhone(Objects.requireNonNull(Objects.requireNonNull(mPhoneViewModel.getAllPhones().getValue()).get(position)));
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.ClearAllData)
        {
            Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_LONG).show();
            mPhoneViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            Phone phone = new Phone(bundle.getString("manufacturer"), bundle.getString("model"), bundle.getString("version"), bundle.getString("website"));
            mPhoneViewModel.insert(phone);

        }

        else if (resultCode == EDIT_CODE)
        {
            Toast.makeText(MainActivity.this, "Działa", Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Phone phone = new Phone(bundle.getString("manufacturer"), bundle.getString("model"), bundle.getString("version"), bundle.getString("website"), bundle.getLong("id"));
            mPhoneViewModel.updatePhone(phone);

            // mPhoneViewModel.updatePhone(phone);


        }
        else {
            Log.e(TAG, "Unknown result");
        }

    }


    @Override
    public void onPhoneClick(Phone phone) {
        Intent intent = new Intent(MainActivity.this, addToBD.class);
        intent.putExtra("id", phone.getFie_mId());
        intent.putExtra("manufacturer", phone.getFie_mOEM());
        intent.putExtra("model", phone.getFie_mModel());
        intent.putExtra("version", phone.getFie_mVersion());
        intent.putExtra("web", phone.getFie_mWebsite());
        startActivityForResult(intent, EDIT_CODE);
    }
}