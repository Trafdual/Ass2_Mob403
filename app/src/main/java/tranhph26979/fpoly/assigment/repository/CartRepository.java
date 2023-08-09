package tranhph26979.fpoly.assigment.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tranhph26979.fpoly.assigment.models.MessModel;
import tranhph26979.fpoly.assigment.retrofit.FoodAppApi;
import tranhph26979.fpoly.assigment.retrofit.RetrofitInstance;

public class CartRepository {
    private FoodAppApi api;
    MutableLiveData<MessModel> data;

    public CartRepository() {
        api = RetrofitInstance.getRetrofit().create(FoodAppApi.class);
        data = new MutableLiveData<>();

    }

    public void checkOut(int iduser, int amount, double total, String detail) {
        api.postCart(iduser, amount, total, detail).enqueue(new Callback<MessModel>() {
            @Override
            public void onResponse(Call<MessModel> call, Response<MessModel> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MessModel> call, Throwable t) {
                data.postValue(null);
                Log.d("loggg", t.getMessage());
            }
        });
    }

    public MutableLiveData<MessModel> messModelMutableLiveData() {
        return data;
    }
}
