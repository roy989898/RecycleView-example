package pom2.poly.com.userecyclerviewnotlistview;

/**
 * Created by User on 26/1/2016.
 */


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static MyClickListener myClickListener;
    private ArrayList<DataObject> mDataset;

    //create a constructor to receive the DataSet
    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    //TODO:why have a interface MyClickListener here?
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }


    //TODO :why have  on ClickListener?
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    //Because the View Holder is in the Asapter for Reycle view, we need
    // to to create our own ViewHolder first ,the view holder hold all the view in a
    // Iteam of the Recycle View,we define what will show in each Iteam of the Recycle view by the XML,
    // recycleriew_iteam.xml,in that,it has two TextView
    //TODO:why implement View.OnClickListener  and overview onClick.Becuase the Recycle view do't have a onClickListerner,so we need to set the onItemClicl to each view first,than use the  interface  MyClickListener to allow define the what will do after click the view.
    // TODO The setOnItemClickListener is a method to accept the myClickListener object
    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }
//here will return the ViewHolder we create,so we need to inflate the XML to view that we decide what will show in each view
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }


    //bind the data to the view,view get from the view holder
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getmText1());
        holder.dateTime.setText(mDataset.get(position).getmText2());
    }

    //add iteam to the Dataset,and notify change to the adtapter.
    public void addItem(DataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    //how many iteam,should be the same with the nuber of the Data set size
    @Override
    public int getItemCount() {
        return mDataset.size();
    }





}
