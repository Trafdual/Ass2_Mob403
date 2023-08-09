package tranhph26979.fpoly.assigment.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tranhph26979.fpoly.assigment.models.MealDetailModel;
import tranhph26979.fpoly.assigment.models.MealModel;
import tranhph26979.fpoly.assigment.retrofit.FoodAppApi;
import tranhph26979.fpoly.assigment.retrofit.RetrofitInstance;

public class MealDetailRespository {
   private FoodAppApi appApi;

    public MealDetailRespository() {
        appApi= RetrofitInstance.getRetrofit().create(FoodAppApi.class);
    }
    public MutableLiveData<MealDetailModel> getMealDetail(int id){
        MutableLiveData<MealDetailModel> data=new MutableLiveData<>();
appApi.getMealsDetail(id).enqueue(new Callback<MealDetailModel>() {
    @Override
    public void onResponse(Call<MealDetailModel> call, Response<MealDetailModel> response) {
        data.setValue(response.body());
    }

    @Override
    public void onFailure(Call<MealDetailModel> call, Throwable t) {
data.setValue(null);
Log.d("logg",t.getMessage());
    }
});
        return data;
    }
}
