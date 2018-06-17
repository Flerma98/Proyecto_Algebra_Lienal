package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

public class U1_ConversionesRPE extends AppCompatActivity {

    EditText txtA, txtB, txtConvertirREC, txtConvertirPOL,txtConvertirEXP;
    Spinner spnConvertirDe;
    Button btnResultado;
    TextView verR, verAng, verI;
    int opt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1__conversiones_rpe);

        verR= (TextView)findViewById(R.id.verR);
        verAng= (TextView)findViewById(R.id.verAng);
        verI= (TextView)findViewById(R.id.verI);
        txtConvertirREC= (EditText)findViewById(R.id.txtResultadoREC);
        txtConvertirPOL= (EditText)findViewById(R.id.txtResultadoPOL);
        txtConvertirEXP= (EditText)findViewById(R.id.txtResultadoEXP);
        txtA= (EditText)findViewById(R.id.txtA);
        txtB= (EditText)findViewById(R.id.txtB);
        spnConvertirDe= (Spinner)findViewById(R.id.spnConvertirDe);
        btnResultado= (Button) findViewById(R.id.btnConvertir);
        String[] opcionesDe = {"Rectangular", "Polar", "Exponencial"};
        spnConvertirDe.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_texto, opcionesDe));

        txtA.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(txtA.getText().length()==0 || txtB.getText().length()==0){
                    btnResultado.setEnabled(false);
                    txtConvertirEXP.setText("");
                    txtConvertirPOL.setText("");
                    txtConvertirREC.setText("");
                }else{
                    btnResultado.setEnabled(true);
                }
                return false;

            }
        });
        txtB.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(txtA.getText().length()==0 || txtB.getText().length()==0){
                    btnResultado.setEnabled(false);
                    txtConvertirEXP.setText("");
                    txtConvertirPOL.setText("");
                    txtConvertirREC.setText("");
                }else{
                    btnResultado.setEnabled(true);
                }
                return false;

            }
        });


        spnConvertirDe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cerrada= (TextView)findViewById(R.id.llavecerrada);
                switch (i){
                    case 0:
                        verI.setText("i");
                        verAng.setText("");
                        verR.setText("");
                        cerrada.setText("]");
                        opt=i;
                        break;
                    case 1:
                        verI.setText("");
                        verAng.setText("Θ:");
                        verR.setText("r:");
                        cerrada.setText("°]");
                        opt=i;
                        break;
                    case 2:
                        verI.setText("");
                        verAng.setText("Θ:");
                        verR.setText("r:");
                        cerrada.setText("°]");
                        opt=i;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtA.getText().toString().equals(".") || txtB.getText().toString().equals(".")) {
                    Toast.makeText(U1_ConversionesRPE.this, "Valores no aceptados", Toast.LENGTH_SHORT).show();
                } else {
                    if (opt == 0) {
                        //Rectangular Rectangular
                        if (Double.parseDouble(txtB.getText().toString()) < 0) {
                            txtConvertirREC.setText(txtA.getText() + " " + txtB.getText() + "i");
                        } else {
                            txtConvertirREC.setText(txtA.getText() + " + " + txtB.getText() + "i");
                        }
                        //Rectangular Polar
                        double a = Double.parseDouble(txtA.getText().toString());
                        double b = Double.parseDouble(txtB.getText().toString());
                        double adiv = 0;
                        double bdiv = 0;
                        double suma = 0;
                        double valor = 0;

                        double angulo = 45; //grados
                        double anguloRadianes = Math.toRadians(angulo);
                        adiv = a;
                        if (a < 0)
                            adiv = adiv * -1;


                        bdiv = b;
                        if (b < 0)
                            bdiv = bdiv * -1;

                        System.out.println(adiv);
                        System.out.println(bdiv);
                        suma = bdiv / adiv;
                        System.out.println(suma);
                        valor = suma;
                        anguloRadianes = Math.atan(valor);
                        angulo = Math.toDegrees(anguloRadianes);
                        if ((a < 0) && (b > 0)) {
                            angulo = 180 - angulo;
                        }
                        if ((a < 0) && (b < 0)) {
                            angulo = 180 + angulo;
                        }
                        if ((a > 0) && (b < 0)) {
                            angulo = 360 - angulo;
                        }
                        double ar = 0;
                        double br = 0;
                        double sumar;

                        ar = a * a;
                        br = b * b;
                        sumar = ar + br;
                        txtConvertirPOL.setText("r= " + String.valueOf(Math.sqrt(sumar)) + "  θ= " + String.valueOf(angulo) + "°");

                        //Rectangular Exponencial
                        double aE = Double.parseDouble(txtA.getText().toString());
                        double bE = Double.parseDouble(txtA.getText().toString());
                        double adivE = 0;
                        double bdivE = 0;
                        double sumaE = 0;
                        double valorE = 0;

                        double anguloE = 45; //grados
                        double anguloRadianesE = Math.toRadians(angulo);
                        adivE = aE;
                        if (aE < 0)
                            adivE = adivE * -1;


                        bdivE = bE;
                        if (bE < 0)
                            bdivE = bdivE * -1;

                        sumaE = bdivE / adivE;
                        valorE = sumaE;
                        anguloRadianesE = Math.atan(valorE);
                        anguloE = Math.toDegrees(anguloRadianesE);
                        if ((aE < 0) && (bE > 0)) {
                            anguloE = 180 - anguloE;
                        }
                        if ((aE < 0) && (bE < 0)) {
                            anguloE = 180 + anguloE;
                        }
                        if ((aE > 0) && (bE < 0)) {
                            anguloE = 360 - anguloE;
                        }

                        double xradarribaE = 0;

                        xradarribaE = 3.1416 * anguloE;
                        xradarribaE = xradarribaE / 180;

                        double arE = 0;
                        double brE = 0;
                        double sumarE;

                        arE = aE * aE;
                        brE = bE * bE;
                        sumarE = arE + brE;

                        txtConvertirEXP.setText(String.valueOf(Math.sqrt(sumarE)) + "  e ^  " + String.valueOf(xradarribaE) + "i");
                    }
                    if (opt == 1) {
                        //Polar Rectangular
                        double r = Double.parseDouble(txtA.getText().toString());
                        double GradoEscrito = Double.parseDouble(txtB.getText().toString());
                        double valorcoseno = 0;
                        double valorseno = 0;
                        double anguloRadianes = Math.toRadians(GradoEscrito);

                        //Coseno
                        valorcoseno = Math.cos(anguloRadianes);
                        valorcoseno = valorcoseno * r;

                        //Seno
                        valorseno = Math.sin(anguloRadianes);
                        valorseno = valorseno * r;

                        if (valorseno > 0)
                            txtConvertirREC.setText(valorcoseno + " + " + valorseno + "i");
                        else
                            txtConvertirREC.setText(valorcoseno + "  " + valorseno + "i");

                        //Polar Polar
                        txtConvertirPOL.setText("r= " + txtA.getText().toString() + "  θ=  " + txtB.getText().toString() + "°");

                        //Polar Exponencial
                        double angulo = Double.parseDouble(txtB.getText().toString());
                        ;
                        double xradarriba = 0;
                        xradarriba = 3.1416 * angulo;
                        xradarriba = xradarriba / 180;

                        txtConvertirEXP.setText(txtA.getText().toString() + "  e  ^  " + String.valueOf(xradarriba) + "°");
                    }
                    if (opt == 2) {
                        //Exponencial Rectangular
                        double anguloexp = Double.parseDouble(txtB.getText().toString());
                        double multi = anguloexp * 180;
                        double division = multi / 3.1416;
                        double opefinal = division;
                        if (division < 0) {
                            opefinal = 360 + division;
                        }
                        double valorcoseno = 0;
                        double valorseno = 0;
                        double r = Double.parseDouble(txtA.getText().toString());
                        double anguloRadianes = Math.toRadians(opefinal);

                        //Coseno
                        valorcoseno = Math.cos(anguloRadianes);
                        valorcoseno = valorcoseno * r;

                        //Seno
                        valorseno = Math.sin(anguloRadianes);
                        valorseno = valorseno * r;

                        if (valorseno > 0)
                            txtConvertirREC.setText(valorcoseno + " + " + valorseno + "i");
                        else
                            txtConvertirREC.setText(valorcoseno + "  " + valorseno + "i");

                        //Exponencial Polar
                        boolean verdadero = false;
                        double angulo = Double.parseDouble(txtB.getText().toString());
                        double arriba = angulo * 180;
                        double divisionP = arriba / 3.1416;
                        do {
                            if (divisionP > 360) {
                                divisionP = divisionP - 360;
                            } else {
                                verdadero = true;
                            }
                        } while (verdadero == false);
                        if (divisionP < 0) {
                            divisionP = 360 + divisionP;
                            txtConvertirPOL.setText("r= " + txtA.getText().toString() + "  θ=  " + String.valueOf(divisionP) + "°");
                        } else {
                            txtConvertirPOL.setText("r= " + txtA.getText().toString() + "  θ=  " + String.valueOf(divisionP) + "°");
                        }

                        //Exponencial Exponencial
                        txtConvertirEXP.setText("r= " + txtA.getText().toString() + "  e  ^  " + txtB.getText().toString() + "°");
                    }
                }
            }
        });
    }
}
