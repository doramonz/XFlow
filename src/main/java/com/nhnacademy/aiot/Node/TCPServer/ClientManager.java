package com.nhnacademy.aiot.Node.TCPServer;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

public class ClientManager {
    private static ClientManager instance;
    private List<Client> clients;
    private JSONObject blackList;

    private ClientManager() {
        super();
        clients = new LinkedList<>();
        blackList = new JSONObject();
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

    public Client getClient(String destination){
        for(Client client : clients){
            if(client.getDestination().equals(destination)){
                return client;
            }
        }
        return null;
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

    public void addBlackList(String ip) {
        blackList.put(ip, true);
    }

    public void removeBlackList(String ip) {
        blackList.remove(ip);
    }

    public boolean isBlackListed(String ip) {
        return blackList.has(ip);
    }

    public String getBlackList() {
        return blackList.toString();
    }
}
