package com.piedweb.randomnetworkport;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int minPort, maxPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minPort = getResources().getInteger(R.integer.min_port);
        maxPort = getResources().getInteger(R.integer.max_port);

        final Random random = new Random();
        final TextView result = findViewById(R.id.result);
        final TextView resultProtocol = findViewById(R.id.result_protocol);
        final TextView resultAccept = findViewById(R.id.result_accept);

        final Button btnGenerate = findViewById(R.id.btn_generate);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText minPortValue = findViewById(R.id.min_port_value);
                if (!minPortValue.getText().toString().isEmpty()) {
                    minPort = Integer.valueOf(minPortValue.getText().toString());
                } else {
                    minPort = getResources().getInteger(R.integer.min_port);
                }
                EditText maxPortValue = findViewById(R.id.max_port_value);
                if (!maxPortValue.getText().toString().isEmpty()) {
                    maxPort = Integer.valueOf(maxPortValue.getText().toString());
                } else {
                    maxPort = getResources().getInteger(R.integer.max_port);
                }

                RadioButton tcpProtocol = findViewById(R.id.protocol_type_tcp);
                RadioButton udpProtocol = findViewById(R.id.protocol_type_udp);
                if (minPort > getResources().getInteger(R.integer.max_port)) {
                    Context context = getApplicationContext();
                    LayoutInflater inflater = getLayoutInflater();
                    View toastView = inflater.inflate(R.layout.toast_min_range_limit, null);
                    Toast toast = new Toast(context);
                    toast.setView(toastView);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                    btnGenerate.setEnabled(false);
                    btnGenerate.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnGenerate.setEnabled(true);
                        }
                    }, 2000);
                } else if (maxPort > getResources().getInteger(R.integer.max_port)) {
                    Context context = getApplicationContext();
                    LayoutInflater inflater = getLayoutInflater();
                    View toastView = inflater.inflate(R.layout.toast_max_range_limit, null);
                    Toast toast = new Toast(context);
                    toast.setView(toastView);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                    btnGenerate.setEnabled(false);
                    btnGenerate.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnGenerate.setEnabled(true);
                        }
                    }, 2000);
                } else if (maxPort <= minPort) {
                    Context context = getApplicationContext();
                    LayoutInflater inflater = getLayoutInflater();
                    View toastView = inflater.inflate(R.layout.toast_range_limit, null);
                    Toast toast = new Toast(context);
                    toast.setView(toastView);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                    btnGenerate.setEnabled(false);
                    btnGenerate.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnGenerate.setEnabled(true);
                        }
                    }, 2000);
                } else {
                    int generatedRandomPort = random.nextInt(maxPort - minPort + 1) + minPort;
                    result.setText(Integer.toString(generatedRandomPort));
                    if (tcpProtocol.isChecked()) {
                        resultProtocol.setText("tcp");
                    } else if (udpProtocol.isChecked()) {
                        resultProtocol.setText("udp");
                    }
                    resultAccept.setText("not accepted");
                }
            }
        });

        final Button btnAccept = findViewById(R.id.btn_accept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultAccept.setText("accepted");
            }
        });
    }

}

