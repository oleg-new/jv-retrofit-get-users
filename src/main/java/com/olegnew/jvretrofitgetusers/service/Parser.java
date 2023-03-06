package com.olegnew.jvretrofitgetusers.service;

import com.olegnew.jvretrofitgetusers.model.Collective;
import com.olegnew.jvretrofitgetusers.model.Collectives;
import com.olegnew.jvretrofitgetusers.model.Items;
import com.olegnew.jvretrofitgetusers.model.UsersData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
public class Parser {
    private static final List<String> tagList = new ArrayList<>(
            Arrays.asList("java", ".net", "docker", "C#"));
    private static final List<String> locationList = new ArrayList<>(
            Arrays.asList("Moldova","Romania"));
    private static final List<Items> resultList = new ArrayList<>();
    private static final List<Items> resultItemsList = new ArrayList<>();
    private static boolean cycle = true;
    private final ItemService itemService = ApiUtils.getItemService();
    private int curentPage = 1;

    public List<Items> getUsers() {
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
        try {
            Response<UsersData> response = listCall.execute();
            if (response.code() == 200) {
                List<Items> items = response.body().getItems();
                selectionByCountryAndTag(items);
                curentPage++;
            } else {
                cycle = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultItemsList;
    }

    public int countOfAnswers(Items current) {
        int answerResult = 0;
        Call<UsersData> answerCall = itemService
                .getUserDetailAnswers(current.getUserId(), "stackoverflow");
        try {
            Response<UsersData> response = answerCall.execute();
            if (response.body() != null && response.body().getItems() != null) {
                answerResult = response.body().getItems().size();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return answerResult;
    }

    public int countOfQuestions(Items currentItem) {
        int questionResult = 0;
        Call<UsersData> questionCall = itemService
                .getUserDetailQuestions(currentItem.getUserId(), "stackoverflow");

        try {
            Response<UsersData> response = questionCall.execute();
            questionResult = response.body().getItems().size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return questionResult;
    }

    private void selectionByCountryAndTag(List<Items> inputList) {
        for (Items currenrItems : inputList) {
            if (currenrItems.getLocation() != null
                    && currenrItems.getCollectives() != null
                    && currenrItems.getCollectives().get(0).getCollective().getTags() != null
                    && isCorrectLocation(currenrItems,locationList)
                    && isTagInCollectives(currenrItems, tagList)) {
                int countAnswer = countOfAnswers(currenrItems);
                if (countAnswer != 0) {
                    currenrItems.setAnswerCount(countAnswer);
                    currenrItems.setQuestionCount(countOfQuestions(currenrItems));
                    resultList.add(currenrItems);
                }
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
