package com.example.herras.ejercicio5;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.herras.ejercicio5.interfaces.OnCasillaSeleccionadaListener;

/**
 * Created by Herras on 09/07/2015.
 */
public class TresEnRaya extends View {

    public static final int VACIA = 0;
    public static final int FICHA_O = 1;
    public static final int FICHA_X = 2;

    private int[][] tablero;
    private int fichaActiva;
    private int xColor;
    private int oColor;

    private OnCasillaSeleccionadaListener listener;

    public TresEnRaya(Context context){
        super(context);
        inicializacion();
    }

    public TresEnRaya(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializacion();
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.TresEnRaya);

        oColor = a.getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE);

        xColor = a.getColor(R.styleable.TresEnRaya_xcolor, Color.RED);

        a.recycle();
    }

    public TresEnRaya(Context context, AttributeSet attrs,int defaultStyle){
        super(context,attrs,defaultStyle);
        inicializacion();
        TypedArray a =
                getContext().obtainStyledAttributes(attrs,
                        R.styleable.TresEnRaya);

        oColor = a.getColor(
                R.styleable.TresEnRaya_ocolor, Color.BLUE);

        xColor = a.getColor(
                R.styleable.TresEnRaya_xcolor, Color.RED);

        a.recycle();
    }

    protected void onDraw(Canvas canvas){
        int alto = getMeasuredHeight();
        int ancho = getMeasuredWidth();

        Paint pBorde = new Paint();
        pBorde.setStyle(Paint.Style.STROKE);
        pBorde.setColor(Color.BLACK);
        pBorde.setStrokeWidth(2);

        //Lineas Verticales
        canvas.drawLine(ancho / 3, 0, ancho / 3, alto, pBorde);
        canvas.drawLine(2 * ancho / 3, 0, 2 * ancho / 3, alto, pBorde);
        //Lineas Horizontales
        canvas.drawLine(0, alto / 3, ancho, alto / 3, pBorde);
        canvas.drawLine(0, 2*alto/3, ancho, 2*alto/3, pBorde);

        //Marco
        canvas.drawRect(0, 0, ancho, alto, pBorde);

        //Fichas
        Paint pMarcaO = new Paint();
        pMarcaO.setStyle(Paint.Style.STROKE);
        pMarcaO.setStrokeWidth(8);
        pMarcaO.setColor(oColor);

        Paint pMarcaX = new Paint();
        pMarcaX.setStyle(Paint.Style.STROKE);
        pMarcaX.setStrokeWidth(8);
        pMarcaX.setColor(xColor);

        //Pinta Fichas en casillas
        for(int fil=0;fil<3;fil++){
            for(int col=0;col<3;col++){

                if(tablero[fil][col]== FICHA_X){
                    canvas.drawLine(
                            (col*(ancho/3))+((ancho/3)*0.1f),
                            (fil*(ancho/3))+((ancho/3)*0.1f),
                            (col*(ancho/3))+((ancho/3)*0.9f),
                            (fil*(ancho/3))+((ancho/3)*0.9f),
                            pMarcaX
                    );
                    canvas.drawLine(
                            (col*(ancho/3))+((ancho/3)*0.1f),
                            (fil*(ancho/3))+((ancho/3)*0.9f),
                            (col*(ancho/3))+((ancho/3)*0.9f),
                            (fil*(ancho/3))+((ancho/3)*0.1f),
                            pMarcaX
                    );
                }else if(tablero[fil][col]== FICHA_O){
                    canvas.drawCircle(
                            col*(ancho/3)+(ancho/6),
                            fil*(ancho/3)+(ancho/6),
                            (ancho / 6) * 0.8f,
                            pMarcaO
                    );
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        int fil = (int)(event.getY() / (getMeasuredHeight()/3));
        int col = (int) (event.getX() / (getMeasuredWidth()/3));
        tablero[fil][col] = fichaActiva;

        alternarFichaActiva();

        if(listener!= null){
            listener.onCasillaSeleccionada(fil,col);
        }

        this.invalidate();
        return super.onTouchEvent(event);
    }

    public void setOnCasillaSeleccionadaListener(OnCasillaSeleccionadaListener l){
        listener = l;
    }

    private void inicializacion(){
        tablero = new int [3][3];
        limpiar();

        fichaActiva = FICHA_X;
        xColor = Color.RED;
        oColor = Color.GREEN;
    }

    public void limpiar(){
        for(int i=0;i>3;i++){
            for(int j=0;j>3;j++){
                tablero[i][j] = VACIA;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int ancho = calcularAncho(widthMeasureSpec);
        int alto = calcularAlto(heightMeasureSpec);

        if(ancho>alto) {
            alto = ancho;
        }else{
            ancho = alto;
        }
        setMeasuredDimension(ancho, alto);
    }

    private int calcularAlto(int limitesSpec){
        int res = 100;
        int modo = MeasureSpec.getMode(limitesSpec);
        int limite = MeasureSpec.getSize(limitesSpec);

        if(modo == MeasureSpec.AT_MOST){
            res = limite;
        }else if (modo == MeasureSpec.EXACTLY){
            res = limite;
        }
        return res;
    }

    private int calcularAncho(int limitesSpec)
    {
        int res = 100; //Ancho por defecto

        int modo = MeasureSpec.getMode(limitesSpec);
        int limite = MeasureSpec.getSize(limitesSpec);

        if (modo == MeasureSpec.AT_MOST) {
            res = limite;
        }
        else if (modo == MeasureSpec.EXACTLY) {
            res = limite;
        }

        return res;
    }

    public void alternarFichaActiva()
    {
        if(fichaActiva == FICHA_O)
            fichaActiva = FICHA_X;
        else
            fichaActiva = FICHA_O;
    }

    public void setFichaActiva(int ficha) {
        fichaActiva = ficha;
    }

    public int getFichaActiva() {
        return fichaActiva;
    }

    public void setXColor(int color) { xColor = color; }

    public int getXColor() { return xColor; }

    public void setOColor(int color) { oColor = color; }

    public int getOColor() { return oColor; }

    public void setCasilla(int fil, int col, int valor) { tablero[fil][col] = valor; }

    public int getCasilla(int fil, int col) { return tablero[fil][col]; }
}
