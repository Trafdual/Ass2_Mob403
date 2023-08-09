package tranhph26979.fpoly.assigment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tranhph26979.fpoly.assigment.R;
import tranhph26979.fpoly.assigment.adapter.MealAdapter;
import tranhph26979.fpoly.assigment.databinding.ActivityCategoryBinding;
import tranhph26979.fpoly.assigment.viewModel.CategoryViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    CategoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        initView();
        initData();

    }

    private void initData() {
        int idcate = getIntent().getIntExtra("idcate", 1);
        String namecate = getIntent().getStringExtra("namecate");
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        viewModel.mealModelMutableLiveData(idcate).observe(this, mealModel -> {
            if (mealModel.isSuccess()) {
                MealAdapter adapter = new MealAdapter(mealModel.getResult());
                binding.rcCategory.setAdapter(adapter);
                binding.tvname.setText(namecate + ": " + mealModel.getResult().size());
            }
        });

    }

    private void initView() {
        binding.rcCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rcCategory.setLayoutManager(layoutManager);
    }
}