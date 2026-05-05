package annotation.validator;


import static util.MyLogger.log;

public class ValidatorV1Main {

    public static void main(String[] args) {
        User user = new User("user1", 0);
        Team team = new Team("", 0);

        try {
            log("== user ==");
            validateUser(user);
        } catch (Exception e) {
            log(e);
        }
        try {
            log("== team ==");
            validateTeam(team);
        } catch (Exception e) {
            log(e);
        }
    }

    private static void validateTeam(Team team) {
        if (team.getName() == null || team.getName().isEmpty()) {
            throw new RuntimeException("name empty");
        }
        if (team.getMemberCnt() < 1 || team.getMemberCnt() > 999) {
            throw new RuntimeException("need 1 < age < 999");
        }

    }

    private static void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException("name empty");
        }
        if (user.getAge() < 1 || user.getAge() > 100) {
            throw new RuntimeException("need 1 < age < 100");
        }
    }
}
