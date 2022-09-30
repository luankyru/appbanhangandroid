package com.example.appbanhang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.APIBanhang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Ultils.Ultils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragmentthemsanpham extends Fragment {
    EditText edttensanpham,edtgiasanpham,edtmotasanpham,edthinhanhsanpham,edtloaisanpham;
    AutoCompleteTextView autoCompleteTextView;
    MainActivity mainActivity;
    ImageView imgcamera;
    Button btnthemsanpham;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanhang apiBanhang ;
    Integer[] mangloai = {1,2,6};
    ArrayAdapter<Integer> listloaispadapter;
    int loai;
    String mediaPath;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themsanpham,container,false);
        imgcamera = view.findViewById(R.id.imgcamera);
        autoCompleteTextView =view.findViewById(R.id.autoComplete);
        listloaispadapter = new ArrayAdapter<Integer>(getContext(),R.layout.listtype,mangloai);
        autoCompleteTextView.setAdapter(listloaispadapter);
        apiBanhang = RetrofitClient.getInstance(Ultils.BASE_URL).create(APIBanhang.class);
        edttensanpham = view.findViewById(R.id.edttensanpham);
        btnthemsanpham = view.findViewById(R.id.btnthemsanpham);
        edtgiasanpham = view.findViewById(R.id.edtgiasanpham);
        edtmotasanpham = view.findViewById(R.id.edtmotasanpham);
        edthinhanhsanpham = view.findViewById(R.id.edthinhanhsanpham);
        Anhxa();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void Anhxa() {

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loai = (int) adapterView.getItemAtPosition(i);
            }
        });

        btnthemsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edttensanpham.getText().toString().isEmpty() && edtgiasanpham.getText().toString().isEmpty() &&edthinhanhsanpham.getText().toString().isEmpty()&& edtmotasanpham.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ các thông tin ", Toast.LENGTH_SHORT).show();
                }
                else{
                    compositeDisposable.add(apiBanhang.themsanpham(edttensanpham.getText().toString(),Long.parseLong(String.valueOf(edtgiasanpham.getText())),edthinhanhsanpham.getText().toString(),edtmotasanpham.getText().toString(),loai)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    sanphammoimodel -> {
                                        if(sanphammoimodel.isSuccess()){
                                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                            ((MainActivity)getActivity()).replaceFragment(new Fragmenthome());
                                            ((MainActivity)getActivity()).setTitleToolbar("Home");
                                        }else{
                                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    }, throwable -> {
                                        Toast.makeText(getContext(), "Lấy dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                                    }));
                }
            }
        });
        imgcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(getActivity())
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

    }
}
