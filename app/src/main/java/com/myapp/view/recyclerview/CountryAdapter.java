package com.myapp.view.recyclerview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ListItemBinding;
import com.myapp.model.room.Country;
import com.myapp.viewmodel.CountryViewModel;

public class CountryAdapter extends ListAdapter<Country,RecyclerView.ViewHolder> {

    private  LifecycleOwner owner;
    private CountryViewModel viewModel;

    public CountryAdapter(@NonNull DiffUtil.ItemCallback<Country> diffCallback, LifecycleOwner _owner,CountryViewModel _viewModel) {
        super(diffCallback);
        owner = _owner;
        viewModel = _viewModel;
    }

    //ビューホルダーオブジェクトを生成
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //DataBindingインスタンス生成
        ListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.list_item, parent, false);

        //RootViewを取得
        View rootView = binding.getRoot();

        //RootViewにbindingを設定
        rootView.setTag(binding);

        return new RecyclerView.ViewHolder (rootView) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //bindingと紐付け
        ListItemBinding binding = (ListItemBinding)holder.itemView.getTag();
        binding.setLifecycleOwner(owner);
        binding.setCountry(getItem(position));
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
    }

    //変化を検知するインナークラス
    public static class CountryDiff extends DiffUtil.ItemCallback<Country> {

        //変化後、インスタンスが同じかどうかを判定
        @Override
        public boolean areItemsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
            return oldItem == newItem;
        }

        //変化後、インスタンスの中身が同じかどうかを判定
        @Override
        public boolean areContentsTheSame(@NonNull Country oldItem, @NonNull Country newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
