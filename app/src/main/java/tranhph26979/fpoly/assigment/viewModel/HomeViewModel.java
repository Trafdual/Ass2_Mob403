package tranhph26979.fpoly.assigment.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import tranhph26979.fpoly.assigment.models.CategoryModel;
import tranhph26979.fpoly.assigment.models.MealModel;
import tranhph26979.fpoly.assigment.repository.CategoryRepository;
import tranhph26979.fpoly.assigment.repository.MealRepository;

public class HomeViewModel extends ViewModel {
    private CategoryRepository categoryRepository;
    private MealRepository mealRepository;

    public HomeViewModel() {

        categoryRepository=new CategoryRepository();
        mealRepository=new MealRepository();
    }
    public MutableLiveData<CategoryModel> categoryModelMutableLiveData(){
        return categoryRepository.getCategory();
    }
    public MutableLiveData<MealModel> mealModelMutableLiveData(int idcate){
        return mealRepository.getMeals(idcate);
    }
}
