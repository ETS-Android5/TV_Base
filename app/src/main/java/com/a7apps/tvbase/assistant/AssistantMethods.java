//Talvez mudar o nome desse package para common (se possivel)
package com.a7apps.tvbase.assistant;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.*;

public class AssistantMethods {
    private Context context;

    public AssistantMethods(Context context) {
        this.context = context;
    }

    public void printConnectionError(VolleyError objError){
        String message = "";
        if (objError instanceof NetworkError){
            message = "Cannot getPosters to Internet...Please check your connection!(NetworkError)";
        }else if(objError instanceof ServerError){
            message = "The server could not be found. Please try again after some time!!";
        }else if (objError instanceof AuthFailureError){
            message = "Cannot getPosters to Internet...Please check your connection!(AuthFailureError)";
        }else if (objError instanceof ParseError){
            message = "Parsing error! Please try again after some time!!";
        }else if (objError instanceof NoConnectionError){
            message = "Cannot getPosters to Internet...Please check your connection! (NoConnectionError)";
        }else if (objError instanceof TimeoutError){
            message = "Connection TimeOut! Please check your internet connection.";
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
