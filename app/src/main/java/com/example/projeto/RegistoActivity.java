package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistoActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        mViewHolder.et_username = findViewById(R.id.et_registo_username);
        mViewHolder.et_password1 = findViewById(R.id.et_registo_password1);
        mViewHolder.et_password2 = findViewById(R.id.et_registo_password2);
        mViewHolder.bt_registar = findViewById(R.id.bt_registo_registar);

        db = new DBHelper(this);

        mViewHolder.bt_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mViewHolder.et_username.getText().toString().trim();
                String password1 = mViewHolder.et_password1.getText().toString().trim();
                String password2 = mViewHolder.et_password2.getText().toString().trim();

                if (username.isEmpty())
                    Toast.makeText(RegistoActivity.this, "Insira o username", Toast.LENGTH_SHORT).show();
                else if (password1.isEmpty() || password2.isEmpty())
                    Toast.makeText(RegistoActivity.this, "Insira e repita a password", Toast.LENGTH_SHORT).show();
                else if (!password1.equals(password2))
                    Toast.makeText(RegistoActivity.this, "Passwords não coincidem", Toast.LENGTH_SHORT).show();
                else {
                    long res = db.Utilizador_Insert(username, password1);
                    if (res > 0) {
                        Toast.makeText(RegistoActivity.this, "Utilizador registado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent i = getIntent();
                        i.putExtra("username", username);
                        setResult(1, i);
                        finish();
                    } else {
                        Toast.makeText(RegistoActivity.this, "Erro ao registar o utilizador", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private class ViewHolder {
        EditText et_username, et_password1, et_password2;
        Button bt_registar;
    }
}