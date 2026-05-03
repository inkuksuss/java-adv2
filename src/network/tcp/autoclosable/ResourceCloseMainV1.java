package network.tcp.autoclosable;

public class ResourceCloseMainV1 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallEx 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseEx 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 r1 = new ResourceV1("r1");
        ResourceV1 r2 = new ResourceV1("r2");

        r1.call();
        r2.callEx();

        System.out.println("자원 정리");
        r2.closeEx();
        r1.closeEx();
    }
}
