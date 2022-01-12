package xyz.ventosa.restester.runner;

public class TestCaseResult extends Result {

    private String failureReason;

    public TestCaseResult(String name) {
        this.setName(name);
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
