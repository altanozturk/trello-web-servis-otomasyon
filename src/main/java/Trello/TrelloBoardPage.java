package Trello;

import io.restassured.response.Response;

public class TrelloBoardPage {
    private TrelloApiClient apiClient;
    private String boardId;
    private String listId;

    public TrelloBoardPage(TrelloApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Response createBoard(String boardName) {
        Response response = apiClient.createBoard(boardName);

        // Response JSON formatında mı kontrolü
        if (response.getContentType().contains("application/json")) {
            try {
                boardId = response.jsonPath().getString("id");
                System.out.println("Board created: " + boardId);
            } catch (Exception e) {
                System.err.println("Failed to parse JSON: " + e.getMessage());
            }
        } else {
            System.err.println("The response is not in JSON format.");
        }

        return response;
    }

    // Yeni bir liste oluşturma fonksiyonu
    public Response createList(String listName) {
        Response response = apiClient.createList(boardId, listName);

        // Response JSON formatında mı kontrolü
        if (response.getContentType().contains("application/json")) {
            try {
                listId = response.jsonPath().getString("id");
                System.out.println("List created: " + listId);
            } catch (Exception e) {
                System.err.println("Failed to parse JSON: " + e.getMessage());
            }
        } else {
            System.err.println("The response is not in JSON format.");
        }
        return response;
    }

    public String createCard(String cardName) {
        Response response = apiClient.createCard(listId, cardName);

        // Response JSON formatında mı kontrolü
        if (response.getContentType().contains("application/json")) {
            try {
                return response.jsonPath().getString("id");
            } catch (Exception e) {
                System.err.println("Failed to parse JSON: " + e.getMessage());
                return null;
            }
        } else {
            System.err.println("The response is not in JSON format.");
            return null;
        }
    }

    public Response updateCard(String cardId, String newName) {
        return apiClient.updateCard(cardId, newName);
    }

    public Response deleteCard(String cardId) {
        return apiClient.deleteCard(cardId);
    }

    public Response deleteBoard() {
        return apiClient.deleteBoard(boardId);
    }
}