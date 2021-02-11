package fr.Laruchsix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder  popup;
    private MainActivity activity;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.data = readData();
        if(this.data.equals("") || this.data.equals(null))
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            this.activity = this;


            // autre pop up
            this.popup = new AlertDialog.Builder(this.activity);
            this.popup.setTitle("Bonjour, merci d'avoir téléchargé MoneyMananger");
            this.popup.setMessage("Pour continuer appuyer sur oui afin de remplir un petit formulaire pour facilier la gestion de l'application. \nPour quitter appuyer sur non.");
            this.popup.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent otherActivity = new Intent(getApplicationContext(), formulairePremiereConnexion.class);
                    startActivity(otherActivity);
                    finish();
                }
            });

            this.popup.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            this.popup.show();


            final Button formAcc = (Button) findViewById(R.id.newAccount);

            formAcc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent otherActivity = new Intent(getApplicationContext(), formulairePremiereConnexion.class);
                    startActivity(otherActivity);
                    finish();
                }
            });
        }
        else
        {
            Intent otherActivity = new Intent(getApplicationContext(), AccountsSelect.class);
            startActivity(otherActivity);
            finish();
        }

    }


    private String readData() {
        String fileName = getResources().getString(R.string.dateUser);
        StringBuilder sb;
        try {
            // Open stream to read file.
            FileInputStream in = this.openFileInput(fileName);

            BufferedReader br= new BufferedReader(new InputStreamReader(in));

            sb= new StringBuilder();
            String s= null;
            while((s= br.readLine())!= null)  {
                sb.append(s).append("\n");
            }
        } catch (Exception e) {
            //Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
            return "";
        }
        return sb.toString();
    }
}