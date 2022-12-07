package com.leduc.restaurantserver.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leduc.restaurantserver.Interface.ItemClickListener;
import com.leduc.restaurantserver.R;

public class OrderViewHolder extends RecyclerView.ViewHolder{
    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderAddress;
    public Button btnEdit, btnRemove, btnDetail;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress = (TextView) itemView.findViewById(R.id.order_address);
        txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone);

        btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
        btnDetail = (Button) itemView.findViewById(R.id.btnDetail);
        btnRemove = (Button) itemView.findViewById(R.id.btnRemove);

    }
}

