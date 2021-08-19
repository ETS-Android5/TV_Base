package com.a7apps.tvbase.connection;

import java.util.ArrayList;

public interface Connect {
    void primaryRequest(String url, final ArrayList<String> posterArray, final ArrayList<String> idArray);
    //São iguais, talvez só precise de duas
    void secondRequest(String url, final ArrayList<String> posterArray, final ArrayList<String> idArray);
    //nesta os dados de array serão muitos, já que precisamos de detalhes
    void thirdRequest(String url, final ArrayList<String> posterArray);
    void fourRequest();
}
