package ru.antoxeeen.buynow.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import ru.antoxeeen.buynow.R;

public class EditGoodsDialog extends AppCompatDialogFragment {
    private EditText edit_goods;
    private EditGoodsDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        String goods_name = getArguments().getString("goods_name");
        final int goods_listId = getArguments().getInt("goods_listId");
        final int goods_id = getArguments().getInt("goods_id");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        edit_goods = view.findViewById(R.id.edit_text_editgoods);
        edit_goods.setText(goods_name);

        builder.setView(view)
                .setTitle("Edit goods")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String goods_name = edit_goods.getText().toString();
                        listener.applyTexts(goods_name, goods_listId, goods_id);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (EditGoodsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement EditGoodsDialogListener");
        }
    }

    public interface EditGoodsDialogListener{
        void applyTexts(String goods_name, int listId, int id);
    }

}
