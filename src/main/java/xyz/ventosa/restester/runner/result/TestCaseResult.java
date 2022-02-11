package xyz.ventosa.restester.runner.result;

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

    public void setFailed(String failureReason) {
        this.failureReason = failureReason;
        this.setStatus(Status.FAILED);
    }
}
