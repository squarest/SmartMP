package com.example.clevercafe.main.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clevercafe.R;
import com.example.clevercafe.entities.Order;
import com.example.clevercafe.main.presentation.MainPresenter;
import com.example.clevercafe.main.presentation.MainView;
import com.example.clevercafe.utils.DialogUtil;

/**
 * Created by Chudofom on 21.09.16.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    //// TODO: 06.08.17 inject dependency with dagger to all adapters
    private MainView view;
    public MainPresenter presenter;
    private Order order;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public EditText productQuantity;
        public TextView productUnits;
        public TextView deleteButton;

        public ViewHolder(View v) {
            super(v);
            productName = v.findViewById(R.id.ingredient_name);
            productQuantity = v.findViewById(R.id.ingredient_quantity);
            productUnits = v.findViewById(R.id.ingredient_units);
            deleteButton = v.findViewById(R.id.delete_button);
        }
    }

    public OrderAdapter(Order order, MainView view, MainPresenter presenter) {
        this.order = order;
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.ingredient_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productName.setText(order.products.get(position).name);
        holder.productQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                order.setProductCount(order.products.get(position).id, Double.valueOf(s.toString()));
                presenter.ingredientsCountChanged(order.products.get(position));
            }
        });
        holder.productQuantity.setText(String.valueOf(order.getProductCount(order.products.get(position).id)));
        holder.productUnits.setText(order.products.get(position).units);
        holder.deleteButton.setOnClickListener(v ->
        {
            DialogUtil.getDeleteAlertDialog(context, "Удаление продукта", "Вы действительно хотите удалить продукт?", (dialogInterface, i) -> {
                order.products.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, order.products.size());
                if (order.products.size() == 0) {
                    view.hideButtonPanel();
                }
            }).show();

        });
    }

    @Override
    public int getItemCount() {
        return order.products.size();
    }
}
