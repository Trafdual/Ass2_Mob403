package tranhph26979.fpoly.assigment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import tranhph26979.fpoly.assigment.R;
import tranhph26979.fpoly.assigment.adapter.CategoryAdapter;
import tranhph26979.fpoly.assigment.adapter.PopularAdapter;
import tranhph26979.fpoly.assigment.databinding.ActivityHomeBinding;
import tranhph26979.fpoly.assigment.listener.CategoryListener;
import tranhph26979.fpoly.assigment.listener.EventClickListener;
import tranhph26979.fpoly.assigment.models.Category;
import tranhph26979.fpoly.assigment.models.Meals;
import tranhph26979.fpoly.assigment.repository.MealRepository;
import tranhph26979.fpoly.assigment.viewModel.HomeViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;

public class HomeActivity extends AppCompatActivity implements CategoryListener, EventClickListener {
ActivityHomeBinding binding;
HomeViewModel homeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=DataBindingUtil.setContentView(this,R.layout.activity_home);
       initData();
       initView();
       binding.flbgio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(),GioHangActivity.class);
               startActivity(intent);
           }
       });

    }

    private void initView() {
        binding.rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.rcCategory.setLayoutManager(layoutManager);

        binding.rcPopular.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1=new GridLayoutManager(this,3);
        binding.rcPopular.setLayoutManager(layoutManager1);
    }

    private void initData() {
        homeViewModel=new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.categoryModelMutableLiveData().observe(this,categoryModel -> {
            if (categoryModel.isSuccess()){
                CategoryAdapter adapter=new CategoryAdapter(categoryModel.getResult(),this);
                binding.rcCategory.setAdapter(adapter);
            }
        });
        homeViewModel.mealModelMutableLiveData(1).observe(this,mealModel -> {
           if (mealModel.isSuccess()){
               PopularAdapter adapter=new PopularAdapter(mealModel.getResult(),this);
               binding.rcPopular.setAdapter(adapter);
           }
        });
    }

    @Override
    public void onCategoryClick(Category category) {
        Intent intent=new Intent(getApplicationContext(), CategoryActivity.class);
        intent.putExtra("idcate",category.getId());
        intent.putExtra("namecate",category.getCategory());
        startActivity(intent);
    }

    @Override
    public void onPoPularClick(Meals meals) {
        Intent intent=new Intent(getApplicationContext(), ShowDetailActivity.class);
        intent.putExtra("id",meals.getIdMeal());
        startActivity(intent);
    }
}