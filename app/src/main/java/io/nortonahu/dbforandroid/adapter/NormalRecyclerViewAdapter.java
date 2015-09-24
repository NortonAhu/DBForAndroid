package io.nortonahu.dbforandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nortonahu.dbforandroid.R;
import io.nortonahu.dbforandroid.app.AppContext;
import io.nortonahu.dbforandroid.bean.Person;
import io.nortonahu.dbforandroid.db.DBManager;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/24 15:43.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalViewHolder>{
    private LayoutInflater mLayoutInflate;
    private Context mContext;

    private String[] mTitles;
    private DBManager mDBManager;

    public NormalRecyclerViewAdapter(Context context, DBManager dbManager) {
        this.mContext = context;
        mLayoutInflate = LayoutInflater.from(context);
        mTitles = AppContext.stringArray(R.array.db_items);
        mDBManager = dbManager;
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflate.inflate(R.layout.item_text, parent, false), mDBManager);
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        holder.mTextView.setText(mTitles[position]);
        holder.mTextView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.textview)
        TextView mTextView;
        private DBManager dbMgr;
        public NormalViewHolder(View itemView, DBManager dbMgr) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.dbMgr = dbMgr;
        }

        @OnClick(R.id.textview)
        void click(@NonNull View view) {
            if(view.getTag() == null){
                Toast.makeText(AppContext.getContext(),"error",Toast.LENGTH_LONG).show();
            }
            switch ((Integer)view.getTag()){
                case 0:

                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }

        private void add(){
            ArrayList<Person> persons = new ArrayList<>();

            Person person1 = new Person("Ella",12,"No 1");
            Person person2 = new Person("Ella2",23,"No 2");
            Person person3 = new Person("Ella3",24,"No 3");
            Person person4 = new Person("Ella4",25,"No 4");
            Person person5 = new Person("Ella5",26,"No 5");
            Person person6 = new Person("Ella6",21,"No 6");

            persons.add(person1);
            persons.add(person2);
            persons.add(person3);
            persons.add(person4);
            persons.add(person5);
            persons.add(person6);

            dbMgr.add(persons);
        }


    }

}