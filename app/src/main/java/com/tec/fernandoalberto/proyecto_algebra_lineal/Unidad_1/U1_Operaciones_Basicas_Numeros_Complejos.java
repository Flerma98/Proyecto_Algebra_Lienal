package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.MainActivity;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class U1_Operaciones_Basicas_Numeros_Complejos extends AppCompatActivity {

    Button btnResultado;
    EditText txtCZ1,txtZ1A,txtZ1I,txtCZ2,txtZ2A,txtZ2I, txtResultado;
    Spinner spnOperacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1__operaciones__basicas__numeros__complejos);
        spnOperacion = (Spinner) findViewById(R.id.spnOperacion);
        String[] operaciones = {"+","-","*","/"};
        spnOperacion.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item, operaciones));
        btnResultado= (Button)findViewById(R.id.btnResultado);
        txtCZ1= (EditText)findViewById(R.id.txtCZ1);
        txtZ1A=(EditText)findViewById(R.id.Z1A);
        txtZ1I=(EditText)findViewById(R.id.Z1i);
        txtCZ2=(EditText)findViewById(R.id.CZ2);
        txtZ2A=(EditText)findViewById(R.id.Z2A);
        txtZ2I=(EditText)findViewById(R.id.Z2i);
        txtResultado=(EditText)findViewById(R.id.txtResultado);
        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCZ1.getText().toString().length()==0 || txtZ1A.getText().toString().length()==0 || txtZ1I.getText().toString().length()==0 || txtCZ2.getText().toString().length()==0 || txtZ2A.getText().toString().length()==0 || txtZ2I.getText().toString().length()==0){
                    Toast.makeText(U1_Operaciones_Basicas_Numeros_Complejos.this, "Campos Incompletos", Toast.LENGTH_SHORT).show();
                }else {
                    if (txtCZ1.getText().toString().equals(".") || txtZ1A.getText().toString().equals(".") || txtZ1I.getText().toString().equals(".") || txtCZ2.getText().toString().equals(".") || txtZ2A.getText().toString().equals(".") || txtZ2I.getText().toString().equals(".")) {
                        Toast.makeText(U1_Operaciones_Basicas_Numeros_Complejos.this, "Valores no aceptados", Toast.LENGTH_SHORT).show();
                    } else {
                        double z1;
                        double z2;
                        double b1;
                        double b2;
                        double sumaarriba;
                        double sumaabajo;
                        double c;
                        double d;
                        double ac;
                        double bd;
                        double ad;
                        double bc;
                        double acbd;
                        double adbc;
                        double sumai;
                        String total;

                        z1 = Double.parseDouble(txtZ1A.getText().toString());
                        z2 = Double.parseDouble(txtZ1I.getText().toString());
                        b1 = Double.parseDouble(txtZ2A.getText().toString());
                        b2 = Double.parseDouble(txtZ2I.getText().toString());

                        double conjugadoz1 = Double.parseDouble(txtCZ1.getText().toString());
                        double conjugadoz2 = Double.parseDouble(txtCZ2.getText().toString());
                        z1 = conjugadoz1 * z1;
                        z2 = conjugadoz1 * z2;

                        b1 = conjugadoz2 * b1;
                        b2 = conjugadoz2 * b2;
                        if (spnOperacion.getSelectedItem().equals("+")) {


                            z1 = z1 + b1;
                            z2 = z2 + b2;
                            if (z2 < 0)
                                txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(z1) + " " + MainActivity.decimalToFraction(z2) + "i"));
                            else
                                txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(z1) + " + " + MainActivity.decimalToFraction(z2) + "i"));
                        }

                        if (spnOperacion.getSelectedItem().equals("-")) {
                            z1 = (z1) - (b1);
                            z2 = (z2) - (b2);
                            if (z2 < 0)
                                txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(z1) + " " + MainActivity.decimalToFraction(z2) + "i"));
                            else
                                txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(z1) + " + " + MainActivity.decimalToFraction(z2) + "i"));
                        }

                        if (spnOperacion.getSelectedItem().equals("*")) {
                            ac = (z1) * (b1);
                            bd = (z2) * (b2);
                            ad = (z1) * (b2);
                            bc = (z2) * (b1);
                            acbd = ac - bd;
                            adbc = ad + bc;
                            if (adbc < 0)
                                txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(acbd) + " " + MainActivity.decimalToFraction(adbc) + "i"));
                            else
                                txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(acbd) + " + " + MainActivity.decimalToFraction(adbc) + "i"));
                        }

                        if (spnOperacion.getSelectedItem().equals("/")) {
                            ac = (z1) * (b1);

                            ad = (z1) * -(b2);
                            bc = (z2) * (b1);
                            bd = (z2) * -(b2);
                            c = (b1) * (b1);
                            d = (b2) * -(-(b2));
                            sumaarriba = ac + (-bd);
                            sumai = ad + bc;
                            sumaabajo = c + d;
                            if (sumai < 0)
                                total = String.valueOf(MainActivity.decimalToFraction(sumaarriba) + "/" + MainActivity.decimalToFraction(sumaabajo) + "  " + MainActivity.decimalToFraction(sumai) + "/" + MainActivity.decimalToFraction(sumaabajo) + "i");
                            else
                                total = String.valueOf(MainActivity.decimalToFraction(sumaarriba) + "/" + MainActivity.decimalToFraction(sumaabajo) + " + " + MainActivity.decimalToFraction(sumai) + "/" + MainActivity.decimalToFraction(sumaabajo) + "i");
                            txtResultado.setText(total);
                        }
                    }
                }
            }
        });
    }
}
