package tranhph26979.fpoly.assigment.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tranhph26979.fpoly.assigment.models.CategoryModel;
import tranhph26979.fpoly.assigment.models.MealModel;

public interface FoodAppApi {
    @GET("category.php")
    Call<CategoryModel> getCategory();
    @POST("meal.php")
    @FormUrlEncoded
    Call<MealModel> getMeals(
            @Field("idcate") int idcate
    );
}
