package com.adgvit.teambassador.ui.payment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.adgvit.teambassador.LogInActivity;
import com.adgvit.teambassador.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class PaymentGpayFragment extends DialogFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_payment_gpay, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        final String gpayEmail= LogInActivity.tempEmail;
        Button gpayContinue=view.findViewById(R.id.paymentGpayContinue);
        final EditText gpayNumber=view.findViewById(R.id.gpaypaytmPhoneNumber);

        gpayContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String GNumber= gpayNumber.getText().toString();
                if (GNumber.equals(""))
                {
                    Snackbar.make(view,"Kindly Enter The Details", BaseTransientBottomBar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
                }
                else
                {
                    DatabaseReference bankPaymentRef= FirebaseDatabase.getInstance().getReference().getRef().child("Payment");
                    try
                    {
                        bankPaymentRef.child(gpayEmail).child("Gpay").child("GpayNumber").setValue(GNumber);
                        dismiss();
                    }
                    catch (Exception e)
                    {
                        Snackbar.make(view,e.toString(), BaseTransientBottomBar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
                    }
                }
            }
        });
    }

}
