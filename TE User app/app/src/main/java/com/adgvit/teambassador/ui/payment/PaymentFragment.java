package com.adgvit.teambassador.ui.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adgvit.teambassador.R;

public class PaymentFragment extends Fragment {

    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_payment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        radioGroup = rootView.findViewById(R.id.paymentRadioGroup);
        Button paymentButton = rootView.findViewById(R.id.paymentButton);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = rootView.findViewById(radioId);

                switch (radioId) {
                    case R.id.paymentGpayRadioButton:
                        PaymentGpayFragment paymentGpayFragment = new PaymentGpayFragment();
                        paymentGpayFragment.show(getChildFragmentManager(), "Gpay");
                        break;
                    case R.id.paymentBankRadioButton:
                        PaymentBankFragment paymentBankFragment = new PaymentBankFragment();
                        paymentBankFragment.show(getChildFragmentManager(), "Bank");
                        break;
                }

            }
        });
    }
}
