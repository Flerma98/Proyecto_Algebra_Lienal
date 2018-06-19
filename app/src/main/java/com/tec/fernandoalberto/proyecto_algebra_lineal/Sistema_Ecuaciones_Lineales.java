package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosTabla;
import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatostxt;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Menores;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import Jama.Matrix;

public class Sistema_Ecuaciones_Lineales extends Fragment {

    private ArrayList<String> listaDatos,arrayList;
    private RecyclerView recycler1, Rrecycler;
    private EditText txtFilas, txtColumnas, txtResultado;
    private AdapterDatosTabla adapter;
    private AdapterDatosResultados adapterR;
    private Button btnCrear, btnObtener;
    private Boolean consistente = true;
    private Boolean inconsistente = true;
    int filas, columnas, cuadricula;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sistema__ecuaciones__lineales, container, false);
        txtFilas = view.findViewById(R.id.txtFSELFila);
        txtColumnas = view.findViewById(R.id.txtFSELColumna);
        btnCrear = view.findViewById(R.id.btnFSELCrear);
        btnObtener = view.findViewById(R.id.btnObtener);
        recycler1 = view.findViewById(R.id.FSELRecycler);
        Rrecycler = view.findViewById(R.id.SELRecycler);
        txtResultado= view.findViewById(R.id.txtResultadoSEL);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtFilas.getText().toString().length()==0 || txtColumnas.getText().toString().length()==0 || Integer.parseInt(txtFilas.getText().toString())!=Integer.parseInt(txtColumnas.getText().toString())){
                    btnObtener.setEnabled(false);
                }else{
                    btnObtener.setEnabled(true);
                    filas= Integer.parseInt(txtFilas.getText().toString());
                    columnas= Integer.parseInt(txtColumnas.getText().toString()) + 1;
                    cuadricula= filas * columnas;
                    listaDatos= new ArrayList<>();
                    for (int i=0; i<cuadricula; i++){
                        listaDatos.add("");
                    }
                    recycler1.setLayoutManager(new GridLayoutManager(getActivity(), columnas));
                    adapter= new AdapterDatosTabla(listaDatos, filas, columnas);
                    recycler1.setAdapter(adapter);

                }
            }
        });

        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                int contador = 0;
                String[][] result = adapter.getData();
                double[][] matriz = new double[result.length][result[0].length];

                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j <= result.length; j++) {
                            ConstraintLayout rootView = (ConstraintLayout) recycler1.getChildAt(contador++);
                            matriz[i][j] = Double.parseDouble(((EditText)rootView.findViewById(R.id.txtListaRecycler)).getText().toString());
                    }
                }
                double[][] matrizDatos = new double[result.length][result[0].length-1];
                    for (int i = 0; i < result.length; i++) {
                        for (int j = 0; j < result[0].length-1; j++) {
                            matrizDatos[i][j] = matriz[i][j];
                        }
                    }
                    double[][] MatrizResultados= new double[result.length][1];
                    double[]matrizr= new double[result.length];
                    for (int i = 0; i < result.length; i++) {
                        MatrizResultados[i][0]= matriz[i][result[0].length-1];
                        matrizr[i]=  matriz[i][result[0].length-1];
                    }
                    double[][] inversa = invert(matrizDatos);


                  Jama.Matrix matrixinversa = new Jama.Matrix(inversa);
                    Jama.Matrix matrixR = new Jama.Matrix(MatrizResultados);

                    double[][] SEL = matrixinversa.times(matrixR).getArray();
                arrayList = new ArrayList<>();
                for (int i = 0; i < matriz.length; i++) {
                            arrayList.add(String.valueOf(MainActivity.decimalToFraction(SEL[i][0])));
                }
                Cargar_Matriz(matrizDatos, matrizr);
                Rrecycler.setLayoutManager(new GridLayoutManager(getActivity(), columnas-1));
                adapterR = new AdapterDatosResultados(arrayList, filas, columnas);
                Rrecycler.setAdapter(adapterR);
                Rrecycler.setEnabled(false);
            }catch (Exception e){
                Toast.makeText(getActivity(), "No se puede calcular esta matriz", Toast.LENGTH_SHORT).show();}
            }
        });
        return view;
    }


    private static double Determinante(double m[][])
    {
        if(m.length != m[0].length)
            throw new IllegalStateException("Dimensiones Invalidas");
        if(m.length == 2)
            return m[0][0] * m[1][1] - m[0][1] * m[1][0];
        double det = 0.0;
        for(int i = 0; i < m[0].length; i++)
            det += Math.pow(-1, i) * m[0][i] * Determinante(Complemento(m, 0, i));

        return det;
    }

    private static double[][] Inversa(double matrix[][])
    {
        double inverse[][] = new double[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j) * Determinante(Complemento(matrix, i, j));

        }

        double det = 1.0 / Determinante(matrix);
        for(int i = 0; i < inverse.length; i++)
        {
            for(int j = 0; j <= i; j++)
            {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }

        }

        return inverse;
    }

    private static double[][] Complemento(double matrix[][], int row, int column)
    {
        double minor[][] = new double[matrix.length - 1][matrix.length - 1];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; i != row && j < matrix[i].length; j++)
                if(j != column)
                    minor[i >= row ? i - 1 : i][j >= column ? j - 1 : j] = matrix[i][j];

        }

        return minor;
    }

    public static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for(int i = 0; i < n; i++)
            b[i][i] = 1.0;

        gaussian(a, index);
        for(int i = 0; i < n - 1; i++)
        {
            for(int j = i + 1; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

            }

        }

        for(int i = 0; i < n; i++)
        {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for(int j = n - 2; j >= 0; j--)
            {
                x[j][i] = b[index[j]][i];
                for(int k = j + 1; k < n; k++)
                    x[j][i] -= a[index[j]][k] * x[k][i];

                x[j][i] /= a[index[j]][j];
            }

        }

        return x;
    }

    public static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];
        for(int i = 0; i < n; i++)
            index[i] = i;

        for(int i = 0; i < n; i++)
        {
            double c1 = 0.0;
            for(int j = 0; j < n; j++)
            {
                double c0 = Math.abs(a[i][j]);
                if(c0 > c1)
                    c1 = c0;
            }

            c[i] = c1;
        }

        int k = 0;
        for(int j = 0; j < n - 1; j++)
        {
            double pi1 = 0.0;
            for(int i = j; i < n; i++)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if(pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for(int i = j + 1; i < n; i++)
            {
                double pj = a[index[i]][j] / a[index[j]][j];
                a[index[i]][j] = pj;
                for(int l = j + 1; l < n; l++)
                    a[index[i]][l] -= pj * a[index[j]][l];

            }

        }

    }

    public double[] Cargar_Matriz(double m[][], double r[])
    {
        int aa = r.length;
        int xx = 0;
        int i = 0;
        int s = 0;
        int j = 0;
        int k = 0;
        int x = 0;
        int y = 0;
        int l = 0;
        int p = 0;
        int yy = 0;
        for(i = 0; i <= r.length - 1; i++)
        {
            xx = 0;
            s = 0;
            double c = 0.0D;
            double d = m[i][i];
            for(s = 0; s <= r.length - 1; s++)
            {
                m[i][s] = m[i][s] / d;
                if(m[r.length - 1][s] == 0.0)
                    xx++;
            }

            r[i] = r[i] / d;
            for(j = 0; j < r.length; j++)
            {
                for(k = 0; k < r.length; k++){
            }
            if(r[r.length - 1] == 0.0 && xx == aa)
            {

            }
                txtResultado.setText("Consistente con Soluciones Infinitas");
                i = aa;
                yy = aa;
                consistente = false;
            }
            if(r[r.length - 1] != 0.0 && xx == aa)
            {
                txtResultado.setText("Inconsistente sin Solución");
                i = aa;
                yy = aa;
                inconsistente = false;
            }
            for(x = yy; x <= r.length - 1; x++)
            {
                xx = 0;
                if(i == x)
                    continue;
                c = m[x][i];
                for(y = 0; y <= r.length - 1; y++)
                {
                    m[x][y] = m[x][y] - c * m[i][y];
                    if(m[r.length - 1][y] == 0.0)
                        xx++;
                }

                r[x] = r[x] - c * r[i];
                for(l = 0; l < r.length; l++)
                {
                    for(p = 0; p < r.length; p++){

                    }
                }

                if(r[r.length - 1] == 0.0 && xx == aa)
                {
                    txtResultado.setText("Consistente con Soluciones Infinitas");
                    i = aa;
                    consistente = false;
                }
                if(r[r.length - 1] != 0.0 && xx == aa)
                {
                    txtResultado.setText("Inconsistente sin Solución");
                    i = aa;
                    inconsistente = false;
                }
            }

        }

        return r;
    }

}
