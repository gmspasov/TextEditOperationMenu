package com.example.texteditoperationmenu;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    enum TextEditOperations {
        Cut, Copy, Paste, Delete
    }

    private PopupWindow window;
    private TextView tv;
    private EditText et;
    private RecyclerView rv;
    private View constraintLayout;
    private String textInClipboard = new String("default text");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_layout);
        tv = findViewById(R.id.text_view);
        et = findViewById(R.id.edit_text);
        et.setText("Text");

        List<StringWithIcon> items = new ArrayList<StringWithIcon>(4);



        items.add(new StringWithIcon("cut", R.drawable.ic_menu_cut, TextEditOperations.Cut));
        items.add(new StringWithIcon("copy", R.drawable.ic_menu_copy, TextEditOperations.Copy));
        items.add(new StringWithIcon("paste", R.drawable.ic_menu_paste, TextEditOperations.Paste));
        items.add(new StringWithIcon("delete", R.drawable.ic_menu_delete, TextEditOperations.Delete));

        rv = new RecyclerView(this);
        rv.setAdapter(new PopupAdapter(items, this, this::applyOperation));
        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(RecyclerView.HORIZONTAL);
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        rv.setLayoutManager(llm);

        CardView cardView = new CardView(this);
        cardView.addView(rv);
        window = new PopupWindow(cardView, -2, -2);
    }

    private void applyOperation(TextEditOperations textEditOperations) {
        switch (textEditOperations) {
            case Cut:
                cutText();
                break;

            case Copy:
                copyText();
                break;

            case Paste:
                pasteText(textInClipboard);
                break;

            case Delete:
                deleteText();
                break;

        }
    }

    public void cutText() {
        textInClipboard = "";
        textInClipboard = et.getText().toString().substring(et.getSelectionStart(), et.getSelectionEnd());
        tv.setText("");
    }

    public void copyText() {
        textInClipboard = "";
        textInClipboard = et.getText().toString().substring(et.getSelectionStart(), et.getSelectionEnd());

    }

    public void deleteText() {
        tv.setText("");
    }

    public void pasteText(String g) {
        tv.setText(g);
    }


    public void demo(MotionEvent event) {
        if (!window.isShowing()) {
            window.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, (int) event.getX(), (int) event.getY());


        } else {
            window.dismiss();
        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                demo(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                Log.d("TAG", "some other action : " + event.getAction());
        }
        return super.onTouchEvent(event);
    }
}
