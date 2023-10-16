package com.nhnacademy.aiot.Node.Fibo;

public class InputOutputNode extends ActiveNode {
    private InputPort[] peerPorts;
    private InputPort[] ports;
    private boolean shit = false;

    public InputOutputNode(int peerCount, int portCount) {
        super();
        peerPorts = new InputPort[peerCount];
        ports = new InputPort[portCount];
    }

    public void connect(int index, InputPort port) {
        peerPorts[index] = port;
    }

    public void getInputPort(int index, InputPort port) {
        ports[index] = port;
    }

    public void output(Message message) {
        for (int i = 0; i < peerPorts.length; i++) {
            peerPorts[i].put(message);
        }
    }

    public void setFFFFFF() {
        shit = true;
    }

    public Message getMessage(int index) {
        return ports[index].get();
    }

    @Override
    public void preProcess() {

    }

    @Override
    public void postProcess() {

    }

    @Override
    public void process() {
        boolean hasMessage = true;
        for (int i = 0; i < ports.length; i++) {
            if (!ports[i].hasMessage()) {
                hasMessage = false;
            }
        }
        if (hasMessage) {
            int sum = 0;
            for (int i = 0; i < ports.length; i++) {
                Message message = ports[i].get();
                sum += message.getData();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            output(new Message(sum));
            if (shit)
                System.out.println("Fibo : " + sum);
        }
    }
}
