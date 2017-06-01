package fuzhiyan.bwei.com.recycleviewdemo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<MyBean.ListBean> list=new ArrayList<>();
    private SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recycle);
        swipe= (SwipeRefreshLayout) findViewById(R.id.swipe);
        initDate();
        //上下文，水平或者垂直， false/true
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter=new RecyclerAdapter(this,list);
        recyclerView.setAdapter(recyclerAdapter);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Toast.makeText(MainActivity.this, " 刷新", Toast.LENGTH_SHORT).show();
                        list.clear();
                        initDate();
                        swipe.setRefreshing(false);
                    }
                }, 1000);


            }



        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    list.addAll(list);
                    Toast.makeText(MainActivity.this, "加载", Toast.LENGTH_SHORT).show();
                    recyclerAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
//        recyclerView.setRecyclerListener();
//        recyclerView.addItemDecoration(new D);
    }

    private void initDate() {
        RequestParams params=new RequestParams(Url.url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("EEE",""+result);
                Gson gson=new Gson();
                MyBean myBean = gson.fromJson(result, MyBean.class);
//                list=new ArrayList<MyBean.ListBean>();
                List<MyBean.ListBean> data = myBean.getList();
                list.addAll(data);
                recyclerAdapter.notifyDataSetChanged();

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }
}
