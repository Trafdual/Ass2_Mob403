package tranhph26979.fpoly.assigment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tranhph26979.fpoly.assigment.R;
import tranhph26979.fpoly.assigment.databinding.ActivityGioHangBinding;

import androidx.databinding.DataBindingUtil;

public class GioHangActivity extends AppCompatActivity {
ActivityGioHangBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=DataBindingUtil.setContentView(this,R.layout.activity_gio_hang);
       binding.checkout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),OrderConfirmActivity.class));
           }
       });
    }
}