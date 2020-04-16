package co.yactech.covid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import co.yactech.covid_19.Model.Doctor;
import co.yactech.covid_19.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
void onItemClick(int position);
//void onCancelClick(int position);
//void onDoneClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    private Context mcontext;
    private List<Doctor> mData;

    public RecyclerViewAdapter(Context mcontext, List<Doctor> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater= LayoutInflater.from(mcontext);
        view=inflater.inflate(R.layout.doctor_row_item,parent,false);

        return new MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getUsername());
        holder.address.setText(mData.get(position).getEmail());
        holder.speciality.setText(mData.get(position).getAddress());
    //    holder.id.setText(mData.get(position).getPid());
       // holder.date.setText(mData.get(position).getApp_date());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView address;
        TextView speciality;
        LinearLayout box;
      //  TextView id,date;

//        ImageView img;
//        RadioButton cancel;
//        RadioButton markdone;


        public MyViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);

            name= itemView.findViewById(R.id.D_name);
            address=itemView.findViewById(R.id.address);
            speciality=itemView.findViewById(R.id.speciality);
            box=itemView.findViewById(R.id.box);
         //   img=itemView.findViewById(R.id.patient_detail);
          //  cancel=itemView.findViewById(R.id.cancel);
//            id=itemView.findViewById(R.id.id);
          //  date=itemView.findViewById(R.id.app_date);
          //  markdone=itemView.findViewById(R.id.markdone);

            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!= null){
                        int position =getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });
//            cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener!= null){
//                        int position =getAdapterPosition();
//                        if (position!= RecyclerView.NO_POSITION){
//                            listener.onCancelClick(position);
//                            cancel.setChecked(false);
//                        }
//                    }
//                }
//            });
//            markdone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener!= null){
//                        int position =getAdapterPosition();
//                        if (position!= RecyclerView.NO_POSITION){
//                            listener.onDoneClick(position);
//                            markdone.setChecked(false);
//                        }
//                    }
//                }
//            });


        }
    }
}
