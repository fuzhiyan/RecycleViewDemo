package fuzhiyan.bwei.com.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 * time:
 * author:付智焱
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<MyBean.ListBean> list;
    private LayoutInflater layoutInflater;
    private final int TYPE_0=0;
    private final int TYPE_1=1;

    public RecyclerAdapter(Context context, List<MyBean.ListBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_0){
            View v=layoutInflater.inflate(R.layout.item,parent,false);
            CViewHolder cViewHolder=new CViewHolder(v);

                    return cViewHolder;
        }else if(viewType==TYPE_1){

            View v1=layoutInflater.inflate(R.layout.item1,parent,false);
            TViewHolder tViewHolder=new TViewHolder(v1);

                    return tViewHolder;


        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getType()==0){
            return TYPE_0;

        }else{
            return TYPE_1;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof CViewHolder){
            ((CViewHolder) holder).textView.setText(list.get(position).getTitle());
            x.image().bind(((CViewHolder) holder).imageView,list.get(position).getPic());

        }else if(holder instanceof TViewHolder){
            String[] arr=list.get(position).getPic().split("\\|");
            ((TViewHolder) holder).textView.setText(list.get(position).getTitle());
            if(list.get(position).getType()==2){
                x.image().bind(((TViewHolder) holder).image1,arr[0]);
                x.image().bind(((TViewHolder) holder).image2,arr[1]);
            }
        }
    }
    @Override
    public int getItemCount() {
//        return list!=null?list.size():0;
        return  list.size();
    }
    class CViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public CViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.text);
            imageView= (ImageView) itemView.findViewById(R.id.image);
        }
    }
    class TViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView image1,image2;
        public TViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.item1_text);
            image1= (ImageView) itemView.findViewById(R.id.item1_image);
            image2= (ImageView) itemView.findViewById(R.id.item_image2);

        }
    }
    public void add(int position){

//        list.add(position,);
        notifyDataSetChanged();

    }
    public void remove(int position){

    }
    public interface OnItemClickListener{
        void OnItemClickListeren(int position,View view);
        void onItemLongClickListener(int position,View view);
    }
    public OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

//
//
//
//    @Override
//    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
//
//    }
//
//
////    //绑定数据
////    @Override
////    public void onBindViewHolder(RecyclerAdapter.CViewHolder holder, int position) {
////        holder.textView.setText(list.get(position).getTitle().toString());
//////        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.imageView);
////        x.image().bind(holder.imageView,list.get(position).getPic());
////
////    }
//    //得到条目数
//    @Override
//    public int getItemCount() {
//        return list!=null?list.size():0;
//    }
//
//}
