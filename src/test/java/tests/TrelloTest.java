package tests;

import Trello.TrelloApiClient;
import Trello.TrelloBoardPage;
import io.restassured.response.Response;
import org.junit.Test;

public class TrelloTest {

    @Test
    public void trelloBoardTest() {
        String apiKey = "6278eb0b6837b94229c175e79104ac54";
        String token = "ATTA901bbefb792a9cf955046029d5f183a72368fd27679560db76726553d0f3bfa80163C24A";

        TrelloApiClient apiClient = new TrelloApiClient(apiKey, token);
        TrelloBoardPage boardPage = new TrelloBoardPage(apiClient);

        // Board Oluşturma
        Response createBoardResponse = boardPage.createBoard("Test Board");
        System.out.println("Create Board - Status Code: " + createBoardResponse.getStatusCode());
        System.out.println("Create Board - Response Body: " + createBoardResponse.asString());

        // Kart oluşturabilmek için Liste oluşturma
        Response createListResponse = boardPage.createList("Test List");
        System.out.println("Create List - Status Code: " + createListResponse.getStatusCode());
        System.out.println("Create List - Response Body: " + createListResponse.asString());

        // Kartları Oluşturma
        String card1Id = boardPage.createCard("First Card");
        System.out.println("Card 1 Created, Card 1 Id: "+ card1Id);
        String card2Id = boardPage.createCard("Second Card");
        System.out.println("Card 2 Created, Card 2 Id: "+ card2Id);

        // Rastgele Bir Kartı Güncelleme
        String randomCardId = Math.random() > 0.5 ? card1Id : card2Id;
        Response updateCardResponse = boardPage.updateCard(randomCardId, "Updated Card");
        System.out.println("Update Card - Status Code: " + updateCardResponse.getStatusCode());
        System.out.println("Update Card - Response Body: " + updateCardResponse.asString());

        // Kartları Silme
        Response deleteCard1Response = boardPage.deleteCard(card1Id);
        System.out.println("Delete Card 1 - Status Code: " + deleteCard1Response.getStatusCode());
        System.out.println("Delete Card 1 - Response Body: " + deleteCard1Response.asString());

        Response deleteCard2Response = boardPage.deleteCard(card2Id);
        System.out.println("Delete Card 2 - Status Code: " + deleteCard2Response.getStatusCode());
        System.out.println("Delete Card 2 - Response Body: " + deleteCard2Response.asString());

        // Board Silme
        Response deleteBoardResponse = boardPage.deleteBoard();
        System.out.println("Delete Board - Status Code: " + deleteBoardResponse.getStatusCode());
        System.out.println("Delete Board - Response Body: " + deleteBoardResponse.asString());
    }
}