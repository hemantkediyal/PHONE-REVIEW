package com.example.hemant.minor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private EditText email,password;
    private Button sign_in_register,googlesignin;
   // RequestQueue queue = Volley.newRequestQueue(this);
    private RequestQueue requestQueue;
    private static final String URL = "http://minorjiit.comli.com/user_control.php";
    private StringRequest request;
    public TextView textView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        final String s = email.getText().toString();
       /* if (s == "") {
            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
            dialog.setTitle("Enter email.id");
            dialog.show();
        } else {*/
          /* public void getting(View view)
           {
               Intent intent=new Intent(this,fetchActivity.class);
               startActivity(intent);

           }*/
            password = (EditText) findViewById(R.id.password);
            sign_in_register = (Button) findViewById(R.id.sign_in_register);

            textView = (TextView) findViewById(R.id.textView);
            requestQueue = Volley.newRequestQueue(this);
            sign_in_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* if (s == "") {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        dialog.setTitle("Enter email.id");
                        dialog.show();
                    }
                    else
                    {*/
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.contains("invalid"))
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                                dialog.setTitle("email type is invalid!!!");
                                dialog.show();
                            }
                            if(response.contains("pr"))
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                                dialog.setTitle("password required!!!");
                                dialog.show();
                            }
                            if (response.contains("success")) {
                                startActivity(new Intent(getApplicationContext(), welcome2Activity.class));
                            }
                            if (response.contains("wrong password")) {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                                dialog.setTitle("Wrong Password");
                                dialog.show();
                            }
                            if (response.contains("failed")) {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                                dialog.setTitle("Error Occured");
                                dialog.show();
                            }
                            //Toast.makeText(getApplicationContext(), "pahuncha", Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                textView.setText(response.toString());
                                if (jsonObject.names().get(0).equals("success  ")) {
                                    Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                    // startActivity(new Intent(getApplicationContext(),Welcome.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                textView.setText(response.toString());
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(getApplicationContext(), "error aa gayi", Toast.LENGTH_LONG).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("email", email.getText().toString());
                            hashMap.put("password", password.getText().toString());

                            return hashMap;
                        }
                    };

                    requestQueue.add(request);
                }
            });
        googlesignin = (Button) findViewById(R.id.gog);
        googlesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beg();
            }
        });
    }

    public void getting(View view)
    {
        Intent intent=new Intent(this,fetchActivity.class);
        startActivity(intent);
    }
    public void beg()
    {
        Intent intent=new Intent(this,fetchActivity.class);
        startActivity(intent);
    }
}