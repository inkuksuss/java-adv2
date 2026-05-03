package network.tcp.autoclosable;

public class ResourceCloseMainV2 {

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
        ResourceV1 r1 = null;
        ResourceV1 r2 = null;

        try {
            r1 = new ResourceV1("r1");
            r2 = new ResourceV1("r2");
            r1.call();
            r2.callEx();
        }
        catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        }
        finally {
            if (r2 != null) r2.closeEx();
            if (r1 != null) r1.closeEx();
        }
    }
}
