package tranhph26979.fpoly.assigment.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import tranhph26979.fpoly.assigment.models.MealModel;
import tranhph26979.fpoly.assigment.repository.MealRepository;

public class CategoryViewModel extends ViewModel {
    private MealRepository mealRepository;

    public CategoryViewModel(){
        mealRepository=new MealRepository();
    }
    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate){
        return mealRepository.getMeals(idcate);
    }
}
