package annotation.validator;

public class Team {

    @NotEmpty(message = "팀명 빔")
    private String name;

    @Range(min = 1, max = 999, message = "회원수는 1~999")
    private int memberCnt;

    public Team(String name, int memberCnt) {
        this.name = name;
        this.memberCnt = memberCnt;
    }

    public String getName() {
        return name;
    }

    public int getMemberCnt() {
        return memberCnt;
    }
}
