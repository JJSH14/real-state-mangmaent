package com.example.loborems.modules;

import com.example.loborems.modules.Client;
import com.example.loborems.modules.DOAClient;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        // Instantiate the DOAClient
        DOAClient doaClient = new DOAClient();

        // Create a new Client
        Client newClient = new Client( "2","John Doe", "johndoe@example.com", "1234567890", "Property A", "Admin");

        // Save the new Client
        System.out.println("Saving a new client...");
        doaClient.save(newClient);
        System.out.println("Client saved successfully!");

        // Retrieve the Client by ID
        System.out.println("Finding client by ID...");
        Client foundClient = doaClient.findById(1);
        if (foundClient != null) {
            System.out.println("Client found: " + foundClient.getName());
        } else {
            System.out.println("Client not found.");
        }

        // Update the Client
        System.out.println("Updating client...");
        foundClient.setEmail("newemail@example.com");
        doaClient.update(foundClient);
        System.out.println("Client updated successfully!");

        // Delete the Client
        System.out.println("Deleting client...");
        doaClient.delete(foundClient);
        System.out.println("Client deleted successfully!");

        // Find all Clients (Placeholder implementation in findAll method)
        System.out.println("Finding all clients...");
        List<Client> clients = doaClient.findAll();
        if (clients != null && !clients.isEmpty()) {
            for (Client client : clients) {
                System.out.println("Client: " + client.getName());
            }
        } else {
            System.out.println("No clients found.");
        }
    }
}
