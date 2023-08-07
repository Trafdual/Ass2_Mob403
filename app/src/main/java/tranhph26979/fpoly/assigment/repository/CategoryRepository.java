package tranhph26979.fpoly.assigment.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tranhph26979.fpoly.assigment.models.CategoryModel;
import tranhph26979.fpoly.assigment.retrofit.FoodAppApi;
import tranhph26979.fpoly.assigment.retrofit.RetrofitInstance;

public class CategoryRepository {
    private FoodAppApi foodAppApi;

    public CategoryRepository (){
        foodAppApi= RetrofitInstance.getRetrofit().create(FoodAppApi.class);

    }
    public MutableLiveData<CategoryModel> getCategory(){
        MutableLiveData<CategoryModel> data=new MutableLiveData<>();
        foodAppApi.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.d("logg",t.getMessage());
                data.setValue(null);
            }
        });
        return data;
    }
}
