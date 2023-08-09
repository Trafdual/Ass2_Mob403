package tranhph26979.fpoly.assigment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import tranhph26979.fpoly.assigment.R;
import tranhph26979.fpoly.assigment.databinding.ActivityShowDetailBinding;
import tranhph26979.fpoly.assigment.models.MealDetail;
import tranhph26979.fpoly.assigment.viewModel.ShowDetailViewModel;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

public class ShowDetailActivity extends AppCompatActivity {
ShowDetailViewModel viewModel;
ActivityShowDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    binding=DataBindingUtil.setContentView(this,R.layout.activity_show_detail);
        getData();
    }

    private void getData() {
        int id=getIntent().getIntExtra("id",0);
        viewModel=new ViewModelProvider(this).get(ShowDetailViewModel.class);
        viewModel.mealDetailModelMutableLiveData(id).observe(this,mealDetailModel -> {
            if (mealDetailModel.isSuccess()){
                MealDetail mealDetail=mealDetailModel.getResult().get(0);
                Log.d("logg",mealDetailModel.getResult().get(0).getMeal());
                binding.txtnamefood.setText(mealDetail.getMeal());
                binding.txtprice.setText("$ "+mealDetail.getPrice());
                binding.txtdesc.setText(mealDetail.getInstructions());
                Glide.with(this).load(mealDetail.getStrmealthumb()).into(binding.image);
            }
        });
    }
}