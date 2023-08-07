package tranhph26979.fpoly.assigment.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
     if (retrofit==null){
         retrofit=new Retrofit.Builder().baseUrl("https://trafdual.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
     }
     return retrofit;
    }
}
