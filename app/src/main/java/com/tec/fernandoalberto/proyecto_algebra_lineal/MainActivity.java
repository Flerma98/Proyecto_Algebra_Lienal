package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        //startActivity(new Intent(this, Operaciones_Basicas_Numeros_Complejos.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static String decimalToFraction(Double decimal)
    {
        String fraccion = "";
        String nDecimal = String.valueOf(decimal);
        if(nDecimal.contains("."))
        {
            String despuesPunto = nDecimal.substring(nDecimal.indexOf(".") + 1, nDecimal.length());
            Long multiplicador = Long.valueOf((long)Math.pow(10, despuesPunto.length()));
            double numerador = Math.round(decimal.doubleValue() * (double)multiplicador.longValue());
            double denominador = multiplicador.longValue();
            boolean simplificable = true;
            do{
                for(int contador = 1; contador <= 121; contador++)
                {
                    if(numerador % (double)contador == 0 && denominador % (double)contador == 0){
                        numerador /= contador;
                        denominador /= contador;
                        contador = 1;
                    }else{
                        simplificable = false;
                    }
                }
            } while(simplificable);
            int num = (int)Math.round(numerador);
            int den = (int)Math.round(denominador);
            if(den == 1)
                fraccion = String.valueOf(num);
            else
                fraccion = (new StringBuilder()).append(String.valueOf(num)).append("/").append(String.valueOf(den)).toString();
        } else
        {
            fraccion = String.valueOf(decimal);
        }
        String cuantos = fraccion;
        if(cuantos.length() > 5)
        {
            String val = (new StringBuilder()).append(decimal).append("").toString();
            BigDecimal big = new BigDecimal(val);
            big = big.setScale(4, RoundingMode.HALF_UP);
            fraccion = String.valueOf(big);
            String despuesPunto = fraccion.substring(fraccion.indexOf(".") + 1, fraccion.length());
            int despues = Integer.parseInt(despuesPunto);
            if(despues < 1){
                String valfin = (new StringBuilder()).append(decimal).append("").toString();
                BigDecimal bigfin = new BigDecimal(valfin);
                bigfin = bigfin.setScale(0, RoundingMode.HALF_UP);
                fraccion = String.valueOf(bigfin);
            }
        }
        return fraccion;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_acercademi) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Numeros_Complejos tab1 = new Numeros_Complejos();
                    return tab1;
                case 1:
                    Matrices_y_Determinantes tab2 = new Matrices_y_Determinantes();
                    return tab2;
                case 2:
                    Sistema_Ecuaciones_Lineales tab3 = new Sistema_Ecuaciones_Lineales();
                    return tab3;
                case 3:
                    Espacio_Vectorial tab4 = new Espacio_Vectorial();
                    return tab4;
                default:
                    return null;
            }
        }

            @Override
            public int getCount () {
                // Show 4 total pages.
                return 4;
            }
        }
}
