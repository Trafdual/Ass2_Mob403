package tranhph26979.fpoly.assigment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.paperdb.Paper;
import tranhph26979.fpoly.assigment.R;
import tranhph26979.fpoly.assigment.Utils.Utils;
import tranhph26979.fpoly.assigment.adapter.CartAdapter;
import tranhph26979.fpoly.assigment.databinding.ActivityGioHangBinding;
import tranhph26979.fpoly.assigment.listener.ChangeNumListener;
import tranhph26979.fpoly.assigment.models.Cart;
import tranhph26979.fpoly.assigment.models.MessModel;
import tranhph26979.fpoly.assigment.viewModel.CartViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    ActivityGioHangBinding binding;
    CartViewModel viewModel;
    int item;
    Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gio_hang);
        Paper.init(this);
        initView();
        intiData();
        totalPrice();
        intiControl();
    }

    private void intiControl() {
        viewModel.init();
        viewModel.messModelMutableLiveData().observe(this, new Observer<MessModel>() {
            @Override
            public void onChanged(MessModel messModel) {
                if (messModel.isSuccess()) {
                    Toast.makeText(getApplicationContext(), messModel.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), OrderConfirmActivity.class));
                    Utils.cartList.clear();
                    Paper.book().delete("cart");
                    finish();
                }
            }
        });
        int iduser = 5;
        binding.btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cart = new Gson().toJson(Utils.cartList);
                Log.d("loggg", cart);
                viewModel.checkOut(iduser, item, price, cart);
            }
        });
    }

    private void intiData() {
        List<Cart> carts = Paper.book().read("cart");
        Utils.cartList = carts;
        if (Utils.cartList != null) {
            CartAdapter adapter = new CartAdapter(new ChangeNumListener() {
                @Override
                public void change() {
                    totalPrice();
                }
            }, Utils.cartList, this);
            binding.rcycleCart.setAdapter(adapter);
        } else {
            binding.txtmess.setVisibility(View.VISIBLE);
            binding.scroolview.setVisibility(View.INVISIBLE);
        }
    }

    private void totalPrice() {
        item = 0;
        price = 0.0;
        if (Utils.cartList != null) {
            for (int i = 0; i < Utils.cartList.size(); i++) {
                item = item + Utils.cartList.get(i).getAmount();

            }
            for (int i = 0; i < Utils.cartList.size(); i++) {
                price = price + Utils.cartList.get(i).getAmount() * Utils.cartList.get(i).getMealDetail().getPrice();
            }
        }

        binding.txtitem.setText(String.valueOf(item));
        binding.txtprice.setText("$" + String.valueOf(price));
    }

    private void initView() {
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding.rcycleCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcycleCart.setLayoutManager(layoutManager);
    }
}