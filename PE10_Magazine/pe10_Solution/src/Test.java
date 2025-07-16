public class Test {
    private  String name;
    private  int issue;

    public Test() {
    }

    public Test(String name, int issue) {
        this.name = name;
        this.issue = issue;
    }

    public String getName() {
        return name;
    }

    public int getIssue() {
        return issue;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", issue=" + issue +
                '}';
    }
}
