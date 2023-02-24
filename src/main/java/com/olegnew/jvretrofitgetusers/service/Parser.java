package com.olegnew.jvretrofitgetusers.service;

import com.olegnew.jvretrofitgetusers.model.Collective;
import com.olegnew.jvretrofitgetusers.model.Collectives;
import com.olegnew.jvretrofitgetusers.model.Items;
import com.olegnew.jvretrofitgetusers.model.UsersData;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Parser {
    private static final List<String> tagList = new ArrayList<>(
            Arrays.asList("java", ".net", "docker", "C#"));
    private static final List<String> locationList = new ArrayList<>(
            Arrays.asList("Moldova","Romania"));
    private static List<Items> resultList = new ArrayList<>();
    ItemService itemService = ApiUtils.getItemService();
    private static List<Items> resultItemsList = new ArrayList<>();
    private static boolean cycle = true;
    private int curentPage = 1;


    public List<Items> getAllPage(int  pageNumber) {

    Call<UsersData> listCall = itemService.getitemsList(pageNumber, 100, "desc", "reputation", 223, "stackoverflow");
        listCall.enqueue(new Callback<UsersData>() {
        @Override
        public void onResponse(Call<UsersData> call, Response<UsersData> response) {
            if (response.code() == 200)
            {
                List<Items> items = response.body().getItems();

                resultItemsList = items;
                curentPage++;
            } else {
                cycle = false;
            }
        }

        @Override
        public void onFailure(Call<UsersData> call, Throwable t) {
            t.printStackTrace();
        }
    }) ;
        return resultItemsList;
    }
    private List<Items> selectionByCountryAndTag(List<Items> inputList) {
        for (Items current: inputList) {
            if (isCorrectLocation(current,locationList) && isTagInCollectives(current, tagList)) {
                resultList.add(current);
            }
        }
        return resultList;
    }
    private boolean isCorrectLocation(Items items, List<String> locationList) {
        return locationList
                .stream()
                .anyMatch(location -> items.getLocation().contains(location));
    }

    private boolean isTagInCollectives(Items items, List<String> tagList) {
        return tagList
                .stream()
                .anyMatch(t -> items.getCollectives()
                        .stream()
                        .map(Collectives::getCollective)
                        .map(Collective::getTags)
                        .flatMap(Collection::stream)
                        .collect(Collectors.joining(", "))
                        .contains(t));
    }

}
