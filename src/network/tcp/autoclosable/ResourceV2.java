package network.tcp.autoclosable;

public class ResourceV2 implements AutoCloseable {

    private String name;

    public ResourceV2(String name) {
        this.name = name;
    }

    public void call() {
        System.out.println("call = " + name);
    }

    public void callEx() throws CallException {
        System.out.println("callEx = " + name);
        throw new CallException("ex = " + name);
    }

    @Override
    public void close() throws CloseException {
        System.out.println("close = " + name);
        throw new CloseException("ex = " + name);
    }

    public void closeEx() throws CloseException {
        System.out.println("closeEx " + name);
        throw new CloseException("ex = " + name);
    }
}
