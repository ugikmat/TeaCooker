package com.example.mat.teacooker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mat.teacooker.model.Tea;

import java.util.List;

public class MyTeaRecyclerViewAdapter extends RecyclerView.Adapter<MyTeaRecyclerViewAdapter.ViewHolder> {

    private final List<Tea> mValues;
    private final View.OnClickListener mListener;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private Context context;


    public MyTeaRecyclerViewAdapter(Context context,List<Tea> items, View.OnClickListener listener) {
        mValues = items;
        mListener = listener;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getDuration()+"");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();

                bundle.putString("name",mValues.get(position).getName());
                bundle.putInt("duration",mValues.get(position).getDuration());

                TimerFragment timerFragment = new TimerFragment();
                timerFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.layout_fragment,timerFragment,"fragmentTimer");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Tea mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
