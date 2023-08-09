package tranhph26979.fpoly.assigment.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import tranhph26979.fpoly.assigment.models.Cart;
import tranhph26979.fpoly.assigment.models.MessModel;
import tranhph26979.fpoly.assigment.repository.CartRepository;

public class CartViewModel extends ViewModel {
    private CartRepository cartRepository;
    private MutableLiveData<MessModel> mutableLiveData;
    public void init(){
        cartRepository=new CartRepository();
        mutableLiveData=cartRepository.messModelMutableLiveData();
    }
    public void checkOut(int iduser,int amount,double total,String detail){
        cartRepository.checkOut(iduser, amount, total, detail);

    }
    public MutableLiveData<MessModel> messModelMutableLiveData(){
        return mutableLiveData;
    }

}
