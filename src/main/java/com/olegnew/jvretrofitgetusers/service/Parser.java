package com.olegnew.jvretrofitgetusers.service;

import com.olegnew.jvretrofitgetusers.model.Collective;
import com.olegnew.jvretrofitgetusers.model.Collectives;
import com.olegnew.jvretrofitgetusers.model.Items;
import com.olegnew.jvretrofitgetusers.model.UsersData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Component
public class Parser {
    private static final List<String> tagList = new ArrayList<>(
            Arrays.asList("java", ".net", "docker", "C#", "google-cloud-code"));
    private static final List<String> locationList = new ArrayList<>(
            Arrays.asList("Moldova","Romania", "France"));
    private static Set<Items> resultList = new HashSet<>();
    private static List<Items> resultItemsList = new ArrayList<>();
    //private static int answerResult ;
    private static boolean cycle = true;
    private ItemService itemService = ApiUtils.getItemService();
    private int curentPage = 1;

    public Set<Items> getUsers() {
        while (cycle) {
            getAllPage(curentPage);
        }
        return resultList;
    }

    public List<Items> getAllPage(int pageNumber) {

        Call<UsersData> listCall = itemService.getitemsList(pageNumber,
                100,
                "desc",
                "reputation",
                223,
                "stackoverflow");
        listCall.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                if (response.code() == 200) {
                    List<Items> items = response.body().getItems();
                    selectionByCountryAndTag(items);
                    curentPage++;
                } else {
                    cycle = false;
                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return resultItemsList;
    }

    public int countOfAnswers(Items current) {
        final int[] answerResult = {0};
        Call<UsersData> answertCall = itemService.
                getUserDetailAnswers(current.getUserId(), "stackoverflow");
        answertCall.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                if (response.code() == 200) {
                    answerResult[0] = response.body().getItems().size();
                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {

            }
        });
        return answerResult[0];
    }

    //public int countOfQuestions(Items current) {
    public int countOfQuestions(int current) {
        int questionResult = 0;
        Call<UsersData> questionCall = itemService.getUserDetailQuestions(current, "stackoverflow");

        try {
            Response<UsersData> response = questionCall.execute();
            questionResult = response.body().getItems().size();
            //return questionResult;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionResult;
    }

    private void selectionByCountryAndTag(List<Items> inputList) {
        for (Items currenrItems : inputList) {
            int countAnswer = countOfAnswers(currenrItems);
            if (currenrItems.getLocation() != null
                    && currenrItems.getCollectives() != null
                    && currenrItems.getCollectives().get(0).getCollective().getTags() != null
                    && isCorrectLocation(currenrItems,locationList)
                    && isTagInCollectives(currenrItems, tagList)
                    && countAnswer != 0) {
                currenrItems.setAnswerCount(countAnswer);
                resultList.add(currenrItems);
            }
        }
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
