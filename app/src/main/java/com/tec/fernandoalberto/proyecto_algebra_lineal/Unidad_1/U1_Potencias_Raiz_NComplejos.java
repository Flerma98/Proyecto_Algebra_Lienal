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

public class U1_Potencias_Raiz_NComplejos extends AppCompatActivity {

    EditText txtA, txtB, txtN, txtCalcularPotencia, txtCalcularRaiz;
    Spinner spnConvertirDe;
    Button btnResultado;
    TextView verR, verAng, verI;
    int opt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1__potencias__raiz__ncomplejos);
        verR= (TextView)findViewById(R.id.verR);
        verAng= (TextView)findViewById(R.id.verAng);
        verI= (TextView)findViewById(R.id.verI);
        txtCalcularPotencia= (EditText)findViewById(R.id.txtResultadoPotencia);
        txtCalcularRaiz= (EditText)findViewById(R.id.txtResultadoRaiz);
        txtA= (EditText)findViewById(R.id.txtA);
        txtB= (EditText)findViewById(R.id.txtB);
        txtN= (EditText)findViewById(R.id.txtN);
        spnConvertirDe= (Spinner)findViewById(R.id.spnConvertirDe);
        btnResultado= (Button) findViewById(R.id.btnConvertir);
        String[] opcionesDe = {"Rectangular", "Polar", "Exponencial"};
        spnConvertirDe.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_texto_2, opcionesDe));

        txtA.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(txtA.getText().length()==0 || txtB.getText().length()==0 || txtN.getText().length()==0){
                    btnResultado.setEnabled(false);
                    txtCalcularPotencia.setText("");
                    txtCalcularRaiz.setText("");
                }else{
                    btnResultado.setEnabled(true);
                }
                return false;

            }
        });
        txtB.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(txtA.getText().length()==0 || txtB.getText().length()==0 || txtN.getText().length()==0){
                    btnResultado.setEnabled(false);
                    txtCalcularPotencia.setText("");
                    txtCalcularRaiz.setText("");
                }else{
                    btnResultado.setEnabled(true);
                }
                return false;

            }
        });
        txtN.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(txtA.getText().length()==0 || txtB.getText().length()==0 || txtN.getText().length()==0){
                    btnResultado.setEnabled(false);
                    txtCalcularPotencia.setText("");
                    txtCalcularRaiz.setText("");
                }else{
                    btnResultado.setEnabled(true);
                }
                return false;

            }
        });
        spnConvertirDe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cerrada= (TextView)findViewById(R.id.llavecerradapr);
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
                if (txtA.getText().toString().equals(".") || txtB.getText().toString().equals(".") || txtN.getText().toString().equals(".")) {
                    Toast.makeText(U1_Potencias_Raiz_NComplejos.this, "Valores no aceptados", Toast.LENGTH_SHORT).show();
                } else {
                    if (opt == 0) {
                        //Rectangular - Potencia
                        try {
                            double aR = Double.parseDouble(txtA.getText().toString());
                            double b = Double.parseDouble(txtB.getText().toString());
                            double adiv = 0;
                            double bdiv = 0;
                            double suma = 0;
                            double valor = 0;
                            double angulo = 45; //grados
                            double anguloRadianes = Math.toRadians(angulo);
                            adiv = aR;
                            if (aR < 0)
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
                            if ((aR < 0) && (b > 0)) {
                                angulo = 180 - angulo;
                            }
                            if ((aR < 0) && (b < 0)) {
                                angulo = 180 + angulo;
                            }
                            if ((aR > 0) && (b < 0)) {
                                angulo = 360 - angulo;
                            }
                            double ar = 0;
                            double br = 0;
                            double sumar;
                            ar = aR * aR;
                            br = b * b;
                            sumar = ar + br;
                            sumar = Math.sqrt(sumar);
                            //sumar es r y angulo es angulo
                            double nporang = 0;
                            int n = Integer.parseInt(txtN.getText().toString());
                            sumar = Math.pow(sumar, n);
                            nporang = n * angulo;
                            double cosnanglo = Math.cos(Math.toRadians(nporang));
                            double sennangulo = Math.sin(Math.toRadians(nporang));
                            double multircos = sumar * cosnanglo;
                            double multisen = sumar * sennangulo;
                            if (multisen > 0) {
                                txtCalcularPotencia.setText("Z^" + txtN.getText() + "  =  " + String.valueOf(multircos) + "  +  " + String.valueOf(multisen) + "i");
                            } else {
                                txtCalcularPotencia.setText("Z^" + txtN.getText() + "  =  " + String.valueOf(multircos) + "    " + String.valueOf(multisen) + "i");
                            }

                        } catch (Exception e) {
                        }
                        //Rrectangular Raiz
                        try {
                            double aR = Double.parseDouble(txtA.getText().toString());
                            double b = Double.parseDouble(txtB.getText().toString());
                            double adiv = 0;
                            double bdiv = 0;
                            double suma = 0;
                            double valor = 0;
                            double angulo = 45; //grados
                            double anguloRadianes = Math.toRadians(angulo);
                            adiv = aR;
                            if (aR < 0)
                                adiv = adiv * -1;
                            bdiv = b;
                            if (b < 0)
                                bdiv = bdiv * -1;
                            suma = bdiv / adiv;
                            valor = suma;
                            anguloRadianes = Math.atan(valor);
                            angulo = Math.toDegrees(anguloRadianes);
                            if ((aR < 0) && (b > 0)) {
                                angulo = 180 - angulo;
                            }
                            if ((aR < 0) && (b < 0)) {
                                angulo = 180 + angulo;
                            }
                            if ((aR > 0) && (b < 0)) {
                                angulo = 360 - angulo;
                            }
                            double ar = 0;
                            double br = 0;
                            double sumar;

                            ar = aR * aR;
                            br = b * b;
                            sumar = ar + br;
                            sumar = Math.sqrt(sumar);
                            //Empieza
                            int n = Integer.parseInt(txtN.getText().toString());
                            int k = Integer.parseInt(txtN.getText().toString()) - 1;
                            // sumar= Math.pow(sumar, n);
                            double tresesentaporK = 0;
                            tresesentaporK = 360 * (k - 1);
                            double partearriba = 0;
                            partearriba = angulo + tresesentaporK;
                            double divi = 0;
                            divi = partearriba / n;
                            double cosdivi = 0;
                            cosdivi = Math.cos(Math.toRadians(divi));
                            double sindivi = 0;
                            sindivi = Math.sin(Math.toRadians(divi));
                            double rfinal = 0;
                            rfinal = Math.sqrt(sumar);
                            double rporcos = 0;
                            rporcos = rfinal * cosdivi;
                            double rporsin = 0;
                            rporsin = rfinal * sindivi;
                            if (ar > 0 && b > 0) {
                                if (rporcos < 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin < 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " + " + String.valueOf(rporsin) + "i");
                            }

                            if (ar < 0 && b > 0) {
                                if (rporcos > 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin < 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " + " + String.valueOf(rporsin) + "i");
                            }

                            if (ar > 0 && b < 0) {
                                if (rporcos < 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin > 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " " + String.valueOf(rporsin) + "i");
                            }

                            if (ar < 0 && b < 0) {
                                if (rporcos > 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin > 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " " + String.valueOf(rporsin) + "i");
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (opt == 1) {
                        try {
                            //Polar Potencia
                            double r = Double.parseDouble(txtA.getText().toString());
                            double angulo = Double.parseDouble(txtB.getText().toString());
                            double nporang = 0;
                            int n = Integer.parseInt(txtN.getText().toString());
                            r = Math.pow(r, n);
                            nporang = n * angulo;
                            double cosnanglo = 0;
                            cosnanglo = Math.cos(Math.toRadians(nporang));
                            double sennangulo = 0;
                            sennangulo = Math.sin(Math.toRadians(nporang));
                            double multircos = 0;
                            multircos = r * cosnanglo;
                            double multisen = 0;
                            multisen = r * sennangulo;
                            if (multisen > 0) {
                                txtCalcularPotencia.setText("Z^" + txtN.getText() + " = " + String.valueOf(multircos) + " + " + String.valueOf(multisen) + "i");
                            } else {
                                txtCalcularPotencia.setText("Z^" + txtN.getText() + " = " + String.valueOf(multircos) + "  " + String.valueOf(multisen) + "i");
                            }
                        } catch (Exception e) {
                        }
                        try {
                            //Polar Raiz
                            double r = Double.parseDouble(txtA.getText().toString());
                            double angulo = Double.parseDouble(txtA.getText().toString());
                            int n = Integer.parseInt(txtN.getText().toString());
                            int k = Integer.parseInt(txtN.getText().toString()) - 1;
                            r = Math.pow(r, n);
                            double tresesentaporK = 0;
                            tresesentaporK = 360 * (k - 1);
                            double partearriba = 0;
                            partearriba = angulo + tresesentaporK;
                            double divi = 0;
                            divi = partearriba / n;
                            double cosdivi = 0;
                            cosdivi = Math.cos(Math.toRadians(divi));
                            double sindivi = 0;
                            sindivi = Math.sin(Math.toRadians(divi));
                            double rfinal = 0;
                            rfinal = Math.sqrt(r);
                            double rporcos = 0;
                            rporcos = rfinal * cosdivi;
                            double rporsin = 0;
                            rporsin = rfinal * sindivi;
                            //expo
                            double raexpo = Double.parseDouble(txtA.getText().toString());
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
                            if (valorseno > 0 && valorcoseno > 0) {
                                if (rporcos < 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin < 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " + " + String.valueOf(rporsin) + "i");
                            }

                            if (valorseno < 0 && valorcoseno > 0) {
                                if (rporcos > 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin < 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " + " + String.valueOf(rporsin) + "i");
                            }

                            if (valorseno > 0 && valorcoseno < 0) {
                                if (rporcos < 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin > 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + "  " + String.valueOf(rporsin) + "i");
                            }

                            if (valorseno < 0 && valorcoseno < 0) {
                                if (rporcos > 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin > 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + "  " + String.valueOf(rporsin) + "i");
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (opt == 2) {
                        try {
                            //Exponencial Potencia
                            boolean verdadero = false;
                            double angulo = Double.parseDouble(txtB.getText().toString());
                            double arriba = angulo * 180;
                            double division = arriba / 3.1416;
                            do {
                                if (division > 360) {
                                    division = division - 360;
                                } else {
                                    verdadero = true;
                                }
                            } while (verdadero == false);
                            if (division < 0) {
                                division = 360 + division;
                            }
                            double r = Double.parseDouble(txtA.getText().toString());
                            double nporang = 0;
                            int n = Integer.parseInt(txtN.getText().toString());
                            r = Math.pow(r, n);
                            nporang = n * division;
                            double cosnanglo = Math.cos(Math.toRadians(nporang));
                            double sennangulo = Math.sin(Math.toRadians(nporang));
                            double multircos = r * cosnanglo;
                            double multisen = r * sennangulo;
                            if (multisen > 0) {
                                txtCalcularPotencia.setText("Z^" + txtN.getText() + " = " + String.valueOf(multircos) + " + " + String.valueOf(multisen) + "i");
                            } else {
                                txtCalcularPotencia.setText("Z^" + txtN.getText() + " = " + String.valueOf(multircos) + "  " + String.valueOf(multisen) + "i");
                            }
                        } catch (Exception e) {
                        }
                        try {
                            //Exponencial Raiz
                            boolean verdadero = false;
                            double angulo = Double.parseDouble(txtB.getText().toString());
                            double arriba = angulo * 180;
                            double division = arriba / 3.1416;
                            do {
                                if (division > 360) {
                                    division = division - 360;
                                } else {
                                    verdadero = true;
                                }
                            } while (verdadero == false);
                            if (division < 0) {
                                division = 360 + division;
                            }
                            double r = Double.parseDouble(txtA.getText().toString());
                            int n = Integer.parseInt(txtN.getText().toString());
                            int k = Integer.parseInt(txtN.getText().toString()) - 1;
                            r = Math.pow(r, n);
                            double tresesentaporK = 360 * (k - 1);
                            double partearriba = division + tresesentaporK;
                            double divi = partearriba / n;
                            double cosdivi = Math.cos(Math.toRadians(divi));
                            double sindivi = Math.sin(Math.toRadians(divi));
                            double rfinal = Math.sqrt(r);
                            double rporcos = rfinal * cosdivi;
                            double rporsin = rfinal * sindivi;
                            //PRIMERO A POLAR
                            double anguloexp = Double.parseDouble(txtB.getText().toString());
                            double multi = 0;
                            double divishion = 0;
                            multi = anguloexp * 180;
                            divishion = multi / 3.1416;
                            double opefinal = divishion;
                            if (divishion < 0) {
                                opefinal = 0;
                                opefinal = 360 + divishion;
                            }
                            //AHORA A RECTANGULAR
                            double valorcoseno = 0;
                            double valorseno = 0;
                            double erre = Double.parseDouble(txtA.getText().toString());
                            double anguloRadianes = Math.toRadians(opefinal);
                            //Coseno
                            valorcoseno = Math.cos(anguloRadianes);
                            valorcoseno = valorcoseno * r;
                            //Seno
                            valorseno = Math.sin(anguloRadianes);
                            valorseno = valorseno * r;
                            if (valorseno > 0)
                                txtCalcularRaiz.setText(valorcoseno + " + " + valorseno + "i");
                            else
                                txtCalcularRaiz.setText(valorcoseno + "  " + valorseno + "i");
                            if (valorseno > 0 && valorcoseno > 0) {
                                if (rporcos < 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin < 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " + " + String.valueOf(rporsin) + "i");
                            }

                            if (valorseno < 0 && valorcoseno > 0) {
                                if (rporcos > 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin < 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + " + " + String.valueOf(rporsin) + "i");
                            }

                            if (valorseno > 0 && valorcoseno < 0) {
                                if (rporcos < 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin > 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + "  " + String.valueOf(rporsin) + "i");
                            }

                            if (valorseno < 0 && valorcoseno < 0) {
                                if (rporcos > 0) {
                                    rporcos = rporcos * -1;
                                }
                                if (rporsin > 0) {
                                    rporsin = rporsin * -1;
                                }
                                txtCalcularRaiz.setText("Raíz de " + txtN.getText() + " = " + String.valueOf(rporcos) + "  " + String.valueOf(rporsin) + "i");
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });
    }
}
