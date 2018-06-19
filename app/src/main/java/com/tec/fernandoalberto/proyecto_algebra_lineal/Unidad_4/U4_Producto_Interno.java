package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.MainActivity;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

public class U4_Producto_Interno extends AppCompatActivity {

    EditText txtZ1, txtZ2, txtX1, txtX2, txtResultado, txtY;
    Button btnResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u4__producto__interno);
        txtX1= findViewById(R.id.txtX1);
        txtZ1= findViewById(R.id.txtZ1);
        txtX2= findViewById(R.id.txtX2);
        txtZ2= findViewById(R.id.txtZ2);
        txtY= findViewById(R.id.txtY);
        txtResultado= findViewById(R.id.txtRESY);
        btnResolver= findViewById(R.id.btnCalcularU);
        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtX1.getText().toString().length() == 0 || txtX2.getText().toString().length() == 0 || txtZ1.getText().toString().length() == 0 || txtZ2.getText().toString().length() == 0) {
                    Toast.makeText(U4_Producto_Interno.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                } else {
                    double x1 = Double.parseDouble(txtX1.getText().toString());
                    double x2 = Double.parseDouble(txtX2.getText().toString());
                    double z1 = Double.parseDouble(txtZ1.getText().toString());
                    double z2 = Double.parseDouble(txtZ2.getText().toString());
                    double suma1= x1*z1;
                    double suma2= x2*z2;
                    double suma = (suma1+suma2);
                    double r1 = (Math.pow(x1, 2)) + (Math.pow(x2, 2));
                    double r2 = (Math.pow(z1, 2)) + (Math.pow(z2, 2));
                    double multi= r1*r2;
                    double y= suma / (Math.sqrt(multi));
                    double yarcos = (Math.acos(y))  * (180/Math.PI);
                    txtY.setText("Y= " + MainActivity.decimalToFraction(Math.ceil(yarcos)) + "°");
                    txtResultado.setText("No son Paralelos ni Ortoginales");
                    if (y == 90) {
                        txtResultado.setText("Son Ortogonales");
                    }
                    if (y == 0) {
                        txtResultado.setText("Son Paralelos");
                    }
                }
            }
        });
    }
}
