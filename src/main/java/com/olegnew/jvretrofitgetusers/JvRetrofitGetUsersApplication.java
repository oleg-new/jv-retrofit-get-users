package com.olegnew.jvretrofitgetusers;

import com.olegnew.jvretrofitgetusers.model.Items;
import com.olegnew.jvretrofitgetusers.model.UsersData;
import com.olegnew.jvretrofitgetusers.service.ApiUtils;
import com.olegnew.jvretrofitgetusers.service.ItemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

@SpringBootApplication
public class JvRetrofitGetUsersApplication {

    public static void main(String[] args) {
        System.out.println("Result");
        SpringApplication.run(JvRetrofitGetUsersApplication.class, args);
        ItemService itemService = ApiUtils.getItemService();
        Call<UsersData> listCall = itemService.getitemsList(1, 100, "desc", "reputation", 223, "stackoverflow");
        listCall.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                System.out.println(response.code());
                List<Items> items = response.body().getItems();
                System.out.println(items.toString());
                System.out.println(items.size());
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                t.printStackTrace();
            }
        }) ;

    }

}
