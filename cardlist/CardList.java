package test.berg.cardlist;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;;import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardList extends AppCompatActivity {
    Exercicios newe = new Exercicios("Andar","20");
    ArrayList<Exercicios> exercicios = new ArrayList<Exercicios>();
    Adaptador adaptador = new Adaptador(this,exercicios);
    PopupWindow insert = new PopupWindow();
    PopupWindow satisf = new PopupWindow();

    public boolean novo = true;
    int currentclicked = 0;
    RelativeLayout layoutp;
    RelativeLayout frag;

    int quantos = 1;
    ImageButton confirmar;
    ImageButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adaptador);
        satisf.dismiss();
        delete = (ImageButton)findViewById(R.id.delete);
        confirmar = (ImageButton)findViewById(R.id.confirmar);
        frag = (RelativeLayout)findViewById(R.id.container2);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        layoutp = (RelativeLayout)findViewById(R.id.principal);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onPositionClicked(View view, final int position) {
         
                currentclicked = position;
                novo = false;
                Newexer(view);

            }


        }));
    }
    //adição de objeto
    private void addItem(Exercicios exercicio) {
        exercicios.add(exercicio);
        adaptador.notifyDataSetChanged();
        satisf.dismiss();
    }
    //atualização de objeto
    private void ChangeItem(Exercicios exercicio)
    {
        exercicios.set(currentclicked,exercicio);
        adaptador.notifyDataSetChanged();
    }
    //remoção de objeto
    private void removeItem(Exercicios exercicio)
    {
        exercicios.remove(exercicio);
        adaptador.notifyDataSetChanged();

    }
    //Tela de adição de exercícios
    public void Newexer(View v)
    {
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View CustomView = inflater.inflate(R.layout.cadastro_exercicio, null);
        layoutp = (RelativeLayout) findViewById(R.id.principal);
        insert = new PopupWindow(
                CustomView,RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT,true
        );
        insert.setBackgroundDrawable(new ColorDrawable());
        insert.showAtLocation(layoutp, Gravity.CENTER, 0,0);

        insert.setFocusable(true);
        ImageButton confirmar = (ImageButton)CustomView.findViewById(R.id.confirmar);
        ImageButton delete = (ImageButton)CustomView.findViewById(R.id.delete);
        if(novo) {
            delete.setVisibility(View.INVISIBLE);
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText minutos = (EditText) CustomView.findViewById(R.id.minutos);
                    EditText nome = (EditText) CustomView.findViewById(R.id.nome);
                    Exercicios e = new Exercicios(nome.getText().toString(), minutos.getText().toString());
                    newe = e;
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    Stfpop(CustomView);
                }
            });
        }else{
            delete.setVisibility(View.VISIBLE);
            EditText minutos = (EditText) CustomView.findViewById(R.id.minutos);
            EditText nome = (EditText) CustomView.findViewById(R.id.nome);
            TextView text = (TextView)CustomView.findViewById(R.id.textView) ;
            minutos.setText(exercicios.get(currentclicked).getMinutos());
            nome.setText(exercicios.get(currentclicked).getNomeatividade());
            text.setText("Editar Atividade");
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText newminutos = (EditText) CustomView.findViewById(R.id.minutos);
                    EditText newnome = (EditText) CustomView.findViewById(R.id.nome);
                    ChangeItem(new Exercicios(newnome.getText().toString(), newminutos.getText().toString()+" minutos"));
                    novo = true;
                    insert.dismiss();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(exercicios.get(currentclicked));
                    insert.dismiss();
                    novo = true;
                }
            });
        }

    }
    //pop de nivel de satisfação
    public void Stfpop(View v){
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View CustomView = inflater.inflate(R.layout.satisf, null);
        layoutp = (RelativeLayout) findViewById(R.id.principal);
        satisf = new PopupWindow(
                CustomView,RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT,true
        );
        satisf.setBackgroundDrawable(new ColorDrawable());
        satisf.showAtLocation(layoutp, Gravity.CENTER, 0,0);
        satisf.setFocusable(true);
        final ImageButton primeiro = (ImageButton)CustomView.findViewById(R.id.core1);
        final ImageButton[] cores = new ImageButton[5];
        ImageButton create = (ImageButton)CustomView.findViewById(R.id.create);
        for (int i = 0;i<5;i++)
        {
            String b = primeiro.getResources().getResourceEntryName(primeiro.getId()).replace(primeiro.getResources().getResourceEntryName(primeiro.getId()).substring(primeiro.getResources().getResourceEntryName(primeiro.getId()).length()-1),"");
            b = b + (i+1);
            Toast.makeText(getApplicationContext(), "Single Click on position        :"+b,
                    Toast.LENGTH_SHORT).show();
            cores[i] = (ImageButton)CustomView.findViewById(getResources().getIdentifier(b,"id",getPackageName()));
            cores[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    char b;
                    for(int i = 0;i<quantos;i++)
                    {
                        cores[i].setImageDrawable(getDrawable(R.drawable.core));
                    }
                    b = v.getResources().getResourceEntryName(v.getId()).charAt(4) ;
                    b -= 48;
                    String c = v.getResources().getResourceEntryName(v.getId()).replace(v.getResources().getResourceEntryName(v.getId()).substring(v.getResources().getResourceEntryName(v.getId()).length()-1),"");
                    quantos = (int)b;
                    ImageButton a = (ImageButton)v;
                    for(int i = 0;i<quantos;i++)
                    {

                        cores[i].setImageDrawable(getDrawable(R.drawable.rcore));
                    }
                }

            });
        }

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(newe)
                insert.dismiss();
                satisf.dismiss();

            }
        });

    }








}
