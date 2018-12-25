package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
    private EditText contactName;
    private EditText contactPhoneNum;
    final static  short  CONTACT_PHONE_NUMBER  = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);
        Button mContactBack = (Button)findViewById(R.id.button4);
        mContactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button mContactSave = (Button)findViewById(R.id.button3);
        mContactSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               contactName  = (EditText)findViewById(R.id.editText5);
               contactPhoneNum = (EditText)findViewById(R.id.editText6);

                Toast.makeText(AddContactActivity.this,"已保存联系人",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
    public EditText getContactName(EditText name){
        this.contactName = name;
        return contactName;
    }
    public EditText getContactPhoneNum(EditText phonenumber){
        this.contactPhoneNum = phonenumber;
        return contactPhoneNum;
    }
}
