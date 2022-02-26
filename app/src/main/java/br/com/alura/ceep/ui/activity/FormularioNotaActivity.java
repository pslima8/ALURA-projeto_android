package br.com.alura.ceep.ui.activity;

import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_POSICAO;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.recyclerview.adapter.ListaCoresAdapter;

public class FormularioNotaActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR_INSERE = "Insere nota";
    public static final String TITULO_APPBAR_ALTERA = "Altera nota";
    private int posicaoRecibida = POSICAO_INVALIDA;
    private TextView titulo;
    private TextView descricao;
    private ConstraintLayout corFundoNota;
    private ListaCoresAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        setTitle(TITULO_APPBAR_INSERE);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra(CHAVE_NOTA)){
            setTitle(TITULO_APPBAR_ALTERA);
            Nota notaRecebida = (Nota) dadosRecebidos
                    .getSerializableExtra(CHAVE_NOTA);
            posicaoRecibida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCampos(notaRecebida);
        }
        if (savedInstanceState!=null){
            String corAtual = savedInstanceState.getString("cor");
            corFundoNota.setBackgroundColor(Color.parseColor(corAtual));
        }

        List<NotaCores> todasCores = carregaCores();
        configuraRecyclreview(todasCores);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ColorDrawable background = (ColorDrawable) corFundoNota.getBackground();
        int backgroundInt = background.getColor();
        String corFundoNotaTxt = "#" + Integer.toHexString(backgroundInt);
        outState.putString("cor", corFundoNotaTxt);
    }

    private void configuraRecyclreview(List<NotaCores> todasCores) {
        RecyclerView palhetaCores = findViewById(R.id.lista_cores_recyclerview);
        configuraAdapter(todasCores, palhetaCores);
    }

    private void configuraAdapter(List<NotaCores> todasCores, RecyclerView palhetaCores) {
        adapter = new ListaCoresAdapter(this, todasCores);
        palhetaCores.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaCoresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NotaCores cores, int posicao) {
                String corAtual = cores.getCor();
                corFundoNota.setBackgroundColor(Color.parseColor(corAtual));
            }
        });
    }

    private List<NotaCores> carregaCores() {
        List<NotaCores> todasCores = Arrays.asList(NotaCores.values());
        return todasCores;
    }


    private void preencheCampos(Nota notaRecebida) {
        titulo.setText(notaRecebida.getTitulo());
        descricao.setText(notaRecebida.getDescricao());
        corFundoNota.setBackgroundColor(Color.parseColor(notaRecebida.getCor()));
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
        corFundoNota = findViewById(R.id.formulario_nota_fundo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(ehMenuSalvaNota(item)){
            Nota notaCriada = criaNota();
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota nota) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, nota);
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoRecibida);
        setResult(Activity.RESULT_OK,resultadoInsercao);
    }

    @NonNull
    private Nota criaNota() {
        String tituloTxt = titulo.getText().toString();
        String descricaoTxt = descricao.getText().toString();
        ColorDrawable background = (ColorDrawable) corFundoNota.getBackground();
        int backgroundInt = background.getColor();
        String corFundoNotaTxt = "#" + Integer.toHexString(backgroundInt);
        if (corFundoNotaTxt == null)
            corFundoNotaTxt = "#FFFFFF";
        return new Nota(tituloTxt, descricaoTxt, corFundoNotaTxt);
    }

    private boolean ehMenuSalvaNota(MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }
}
