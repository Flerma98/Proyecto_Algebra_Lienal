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

public class U1_Operaciones_Forma_Polar extends AppCompatActivity {

    Button btnResultado;
    EditText txtZ1A,txtZ1I,txtZ2A,txtZ2I, txtResultado;
    Spinner spnOperacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u1__operaciones__forma__polar);
        spnOperacion = (Spinner) findViewById(R.id.spnOperacion);
        String[] operaciones = {"*","/"};
        spnOperacion.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item_2, operaciones));
        btnResultado= (Button)findViewById(R.id.btnResultado);
        txtZ1A=(EditText)findViewById(R.id.Z1A);
        txtZ1I=(EditText)findViewById(R.id.Z1i);
        txtZ2A=(EditText)findViewById(R.id.Z2A);
        txtZ2I=(EditText)findViewById(R.id.Z2i);
        txtResultado=(EditText)findViewById(R.id.txtResultado);

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtZ1A.getText().toString().length()==0 || txtZ1I.getText().toString().length()==0 || txtZ2A.getText().toString().length()==0 || txtZ2I.getText().toString().length()==0){
                    Toast.makeText(U1_Operaciones_Forma_Polar.this, "Campos Incompletos", Toast.LENGTH_SHORT).show();
                }else {
                    if (txtZ1A.getText().toString().equals(".") || txtZ1I.getText().toString().equals(".") || txtZ2A.getText().toString().equals(".") || txtZ2I.getText().toString().equals(".")) {
                        Toast.makeText(U1_Operaciones_Forma_Polar.this, "Valores no aceptados", Toast.LENGTH_SHORT).show();
                    } else {
                        if (spnOperacion.getSelectedItem().equals("*")) {
                            boolean Ang1 = false;
                            boolean Ang1may = false;
                            boolean Ang2may = false;
                            boolean Ang2 = false;
                            boolean verdadero = false;
                            double RZ1 = Double.parseDouble(txtZ1A.getText().toString());
                            double RZ2 = Double.parseDouble(txtZ2A.getText().toString());
                            double AngZ1 = Double.parseDouble(txtZ1I.getText().toString());
                            double AngZ2 = Double.parseDouble(txtZ2I.getText().toString());
                            if (AngZ1 < 0) {
                                do {
                                    if (AngZ1 < 0) {
                                        AngZ1 = 360 + AngZ1;
                                    }
                                } while (AngZ1 < 0);
                            }


                            if (AngZ1 > 360) {
                                do {
                                    if (AngZ1 > 360) {
                                        AngZ1 = AngZ1 - 360;
                                    }
                                } while (AngZ1 > 360);
                            }


                            if (AngZ2 < 0) {
                                do {
                                    if (AngZ2 < 0) {
                                        AngZ2 = 360 + AngZ2;
                                    }
                                } while (AngZ1 < 0);
                            }

                            if (AngZ2 > 360) {
                                do {
                                    if (AngZ2 > 360) {
                                        AngZ2 = AngZ2 - 360;
                                    }
                                } while (AngZ2 > 360);
                            }


                            double multiR = 0;
                            double sumaAng = 0;
                            multiR = RZ1 * RZ2;
                            sumaAng = AngZ1 + AngZ2;

                            if (sumaAng > 360) {
                                do {
                                    if (sumaAng > 360) {
                                        sumaAng = sumaAng - 360;
                                    }
                                } while (sumaAng > 360);
                            }

                            if (sumaAng > 360) {
                                do {
                                    if (sumaAng > 360) {
                                        sumaAng = sumaAng - 360;
                                    }
                                } while (sumaAng > 360);
                            }

                            if (sumaAng < 0) {
                                do {
                                    if (sumaAng < 0) {
                                        sumaAng = 360 + sumaAng;
                                    }
                                } while (sumaAng < 0);
                            }

                            txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(multiR)) + "  |_  " + String.valueOf(MainActivity.decimalToFraction(sumaAng)) + "°");
                        }
                        if (spnOperacion.getSelectedItem().equals("/")) {
                            boolean Ang1div = false;
                            boolean Ang2div = false;
                            boolean Ang1maydiv = false;
                            boolean Ang2maydiv = false;
                            boolean verdadero = false;
                            double RZ1 = Double.parseDouble(txtZ1A.getText().toString());
                            double RZ2 = Double.parseDouble(txtZ2A.getText().toString());
                            double AngZ1 = Double.parseDouble(txtZ1I.getText().toString());
                            double AngZ2 = Double.parseDouble(txtZ2I.getText().toString());
                            if (AngZ1 < 0) {
                                do {
                                    if (AngZ1 < 0) {
                                        AngZ1 = 360 + AngZ1;
                                    }
                                } while (AngZ1 < 0);
                            }


                            if (AngZ1 > 360) {
                                do {
                                    if (AngZ1 > 360) {
                                        AngZ1 = AngZ1 - 360;
                                    }
                                } while (AngZ1 > 360);
                            }


                            if (AngZ2 < 0) {
                                do {
                                    if (AngZ2 < 0) {
                                        AngZ2 = 360 + AngZ2;
                                    }
                                } while (AngZ1 < 0);
                            }

                            if (AngZ2 > 360) {
                                do {
                                    if (AngZ2 > 360) {
                                        AngZ2 = AngZ2 - 360;
                                    }
                                } while (AngZ2 > 360);
                            }

                            double diviR = 0;

                            diviR = RZ1 / RZ2;
                            double restaAng = 0;
                            restaAng = AngZ1 - AngZ2;

                            if (restaAng < 0) {
                                do {
                                    if (restaAng < 0) {
                                        restaAng = 360 + restaAng;
                                    }
                                } while (restaAng > 360);
                            }

                            if (restaAng > 360) {
                                do {
                                    if (restaAng > 360) {
                                        restaAng = restaAng - 360;
                                    }
                                } while (restaAng < 0);
                            }

                            txtResultado.setText(String.valueOf(MainActivity.decimalToFraction(diviR)) + "  |_  " + String.valueOf(MainActivity.decimalToFraction(restaAng)) + "°");
                        }
                    }
                }
            }
        });
    }
}
