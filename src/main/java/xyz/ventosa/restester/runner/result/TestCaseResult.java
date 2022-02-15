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

    public TestCaseResult setFailed(String expected, String actual) {
        this.failureReason = String.format("Expected: %s, and found %s",expected, actual);
        this.setStatus(Status.FAILED);
        return this;
    }

    public TestCaseResult setError(String errorReason) {
        this.setFailureReason(errorReason);
        this.setStatus(Status.ERROR);
        return this;
    }

    public TestCaseResult setPassed() {
        this.setStatus(Status.PASSED);
        return this;
    }
}
