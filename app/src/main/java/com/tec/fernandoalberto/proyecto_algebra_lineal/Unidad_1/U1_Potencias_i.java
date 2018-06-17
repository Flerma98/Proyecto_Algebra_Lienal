package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

public class U1_Potencias_i extends AppCompatActivity {

    EditText txti;
    EditText txtr;
    Button btnResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1__potencias_i);
        txti = (EditText) findViewById(R.id.txtI);
        txtr = (EditText) findViewById(R.id.txtResultado);
        btnResultado= (Button)findViewById(R.id.btnResultado);
        txti.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(txti.getText().length()==0){
                    btnResultado.setEnabled(false);
                    txtr.setText("");
                }else{
                    btnResultado.setEnabled(true);
                }
                return false;

            }
        });
        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txti.getText().toString().length() == 0) {
                    Toast.makeText(U1_Potencias_i.this, "Campo Vacio", Toast.LENGTH_SHORT).show();
                } else {
                    if (txti.getText().toString().equals(".")) {
                        Toast.makeText(U1_Potencias_i.this, "Valores no aceptados", Toast.LENGTH_SHORT).show();
                    }else {
                        int n = Integer.parseInt(txti.getText().toString());
                        if (n % 2 == 0) {
                            if (((n / 2) % 2) == 0) {
                                txtr.setText("1");
                            } else {
                                txtr.setText("-1");
                            }
                        } else {
                            n = n - 1;
                            if (((n / 2) % 2) == 0) {
                                txtr.setText("i");
                            } else {
                                txtr.setText("-i");
                            }
                        }
                    }
                }
            }
        });
    }
}
