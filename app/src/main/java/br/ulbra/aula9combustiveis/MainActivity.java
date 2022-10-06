package br.ulbra.aula9combustiveis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{

    EditText edCombustivel,edAlcool,ed1,ed2,ed3,ed4,ed5;

    Button btnCalcular;

    private static final DecimalFormat dfTwo = new DecimalFormat("0.00");

    public void mostrarResultado(String mensagem)
    {
        AlertDialog.Builder resultado = new AlertDialog.Builder(MainActivity.this);
        resultado.setTitle("Resultado:");
        resultado.setMessage(mensagem);
        resultado.setNeutralButton("Fechar",null);
        resultado.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCombustivel = findViewById(R.id.edCombustivel);
        edAlcool = findViewById(R.id.edAlcool);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);

        btnCalcular = findViewById(R.id.btCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                double preco,preco2,distribuicao,etanol,estadual,federal,petrobras,resultado,resultado2;

                preco = Double.parseDouble(edCombustivel.getText().toString());
                preco2 = Double.parseDouble(edAlcool.getText().toString());

                distribuicao = Double.parseDouble(ed1.getText().toString()) / 100 ;
                etanol = Double.parseDouble(ed2.getText().toString()) / 100;
                estadual = Double.parseDouble(ed3.getText().toString()) / 100;
                federal = Double.parseDouble(ed4.getText().toString()) / 100;
                petrobras = Double.parseDouble(ed5.getText().toString()) / 100;

                resultado = preco * (distribuicao + etanol + estadual + federal + petrobras + 1);
                resultado2 = preco2 * (distribuicao + etanol + estadual + federal + petrobras + 1);

                if(distribuicao + etanol + estadual + federal + petrobras + 1 > 2)
                {
                    Toast.makeText(MainActivity.this, "ALERTA: Imposto maior que 100%", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(resultado2 / resultado <= 0.7)
                    {
                        mostrarResultado("Vale mais a pena abastecer com Alcool");
                    }
                    else
                    {
                        mostrarResultado("Vale mais a pena abastecer com Gasolina");
                    }

                    mostrarResultado("Preço medio do alcool: R$" + dfTwo.format(resultado2));
                    mostrarResultado("Preço medio da gasolina: R$" + dfTwo.format(resultado));
                }
            }
        });
    }
}