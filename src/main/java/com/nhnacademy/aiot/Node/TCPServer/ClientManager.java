package com.nhnacademy.aiot.Node.TCPServer;

import java.util.LinkedList;
import java.util.List;

public class ClientManager {
    private static ClientManager instance;
    private List<Client> clients;

    private ClientManager() {
        super();
        clients = new LinkedList<>();
    }

    public static ClientManager getInstance() {
        if (instance == null)
            instance = new ClientManager();
        return instance;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void removeClient(int index) {
        clients.remove(index);
    }

    public Client getClient(int index) {
        return clients.get(index);
    }

    public int getClientIndex(Client client) {
        return clients.indexOf(client);
    }

    public int getClientCount() {
        return clients.size();
    }

    public Iterable<Client> getClients() {
        return clients;
    }

    public boolean contains(Client client) {
        return clients.contains(client);
    }
}
