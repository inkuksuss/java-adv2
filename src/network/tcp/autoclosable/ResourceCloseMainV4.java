package network.tcp.autoclosable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallEx 예외 처리");

            for (Throwable throwable : e.getSuppressed()) {
                System.out.println("getSuppressed = " + throwable);
            }

            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseEx 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try (ResourceV2 r1 = new ResourceV2("r1");
        ResourceV2 r2 = new ResourceV2("r2")) {

            r1.call();
            r2.callEx();
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        }
    }
}
