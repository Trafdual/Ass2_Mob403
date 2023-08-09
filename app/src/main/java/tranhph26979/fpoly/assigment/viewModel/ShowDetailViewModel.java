package tranhph26979.fpoly.assigment.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import tranhph26979.fpoly.assigment.models.MealDetailModel;
import tranhph26979.fpoly.assigment.repository.MealDetailRespository;

public class ShowDetailViewModel extends ViewModel {
    private MealDetailRespository mealDetailRespository;

    public ShowDetailViewModel() {
        mealDetailRespository=new MealDetailRespository();

    }
    public MutableLiveData<MealDetailModel> mealDetailModelMutableLiveData(int id){
     return mealDetailRespository.getMealDetail(id);
    }
}
