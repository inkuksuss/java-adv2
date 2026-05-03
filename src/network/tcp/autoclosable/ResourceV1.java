package network.tcp.autoclosable;

public class ResourceV1 {

    private String name;

    public ResourceV1(String name) {
        this.name = name;
    }

    public void call() {
        System.out.println("call = " + name);
    }

    public void callEx() throws CallException {
        System.out.println("callEx = " + name);
        throw new CallException("ex = " + name);
    }

    public void close() {
        System.out.println("close = " + name);
    }

    public void closeEx() throws CloseException {
        System.out.println("closeEx " + name);
        throw new CloseException("ex = " + name);
    }
}
