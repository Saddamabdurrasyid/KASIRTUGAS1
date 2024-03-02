    package com.example.kasir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private EditText etBarang1, etBarang2, etBarang3, etBarang4;
    private RadioGroup radioGroup;
    private RadioButton rbNone, rbGold, rbSilver, rbBronze;
    private Button btnProses;
    private TextView tvTotal;
    private TextView tvResult;

    private int[] hargaBarang = {10000, 12000, 15000, 13000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBarang1 = findViewById(R.id.etBarang1);
        etBarang2 = findViewById(R.id.etBarang2);
        etBarang3 = findViewById(R.id.etBarang3);
        etBarang4 = findViewById(R.id.etBarang4);
        radioGroup = findViewById(R.id.radioGroup);
        rbNone = findViewById(R.id.rbNone);
        rbGold = findViewById(R.id.rbGold);
        rbSilver = findViewById(R.id.rbSilver);
        rbBronze = findViewById(R.id.rbBronze);
        btnProses = findViewById(R.id.btnProses);
        tvTotal = findViewById(R.id.tvTotal);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungTotalHarga();
            }
        });
    }

    private void hitungTotalHarga() {
        int diskon = 0;
        int totalHarga = 0;
        int jumlahBarang1 = 0, jumlahBarang2 = 0, jumlahBarang3 = 0, jumlahBarang4 = 0;

        try {
            jumlahBarang1 = Integer.parseInt(etBarang1.getText().toString());
            jumlahBarang2 = Integer.parseInt(etBarang2.getText().toString());
            jumlahBarang3 = Integer.parseInt(etBarang3.getText().toString());
            jumlahBarang4 = Integer.parseInt(etBarang4.getText().toString());
        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Peringatan");
            builder.setMessage("Mohon isi semua kolom terlebih dahulu. " +
                    "                                               " +
                    "*ISI DENGAN ANGKA 0 JIKA TIDAK ORDER");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return;
        }
            int totalHargaBarang1 = jumlahBarang1 * hargaBarang[0];
            int totalHargaBarang2 = jumlahBarang2 * hargaBarang[1];
            int totalHargaBarang3 = jumlahBarang3 * hargaBarang[2];
            int totalHargaBarang4 = jumlahBarang4 * hargaBarang[3];

            totalHarga += totalHargaBarang1;
            totalHarga += totalHargaBarang2;
            totalHarga += totalHargaBarang3;
            totalHarga += totalHargaBarang4;

            int totalHargaSebelumDiskon = totalHarga;
        if (totalHarga >= 100000) {
             diskon = totalHarga * 10 / 100;
            totalHarga -= diskon;
        }

        int diskonMembership = 0;
        if (radioGroup.getCheckedRadioButtonId() == R.id.rbGold) {
            diskonMembership = 50000;
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbSilver) {
            diskonMembership = 30000;
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rbBronze) {
            diskonMembership = 10000;
        }

        totalHarga -= diskonMembership;


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Bon Transaksi");
        builder.setMessage("Bakso : " + jumlahBarang1 + " x Rp " + hargaBarang[0] + " = Rp " + totalHargaBarang1 + "\n" +
                "Mie Ayam : " + jumlahBarang2 + " x Rp " + hargaBarang[1] + " = Rp " + totalHargaBarang2 + "\n" +
                "Mie Ayam Bakso : " + jumlahBarang3 + " x Rp " + hargaBarang[2] + " = Rp " + totalHargaBarang3 + "\n" +
                "Mie Pangsit : " + jumlahBarang4 + " x Rp " + hargaBarang[3] + " = Rp " + totalHargaBarang4 + "\n" +
                "Total Harga Sebelum Diskon: Rp " + totalHargaSebelumDiskon + "\n" +
                "Diskon Belanja: Rp " + diskon + "\n" +
                "Diskon Keanggotaan: Rp " + diskonMembership + "\n" +
                "---------------------------------------------\n" +
                "Total Bayar: Rp " + totalHarga);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.show();


    }

}