package xyz.ventosa.restester.runner;


public abstract class Result {

    protected Status status = Status.TO_RUN;
    protected double executionTime = 0d;
    protected String name;

    public boolean isExecuted() {
        return status == Status.PASSED || status == Status.FAILED;
    }

    public boolean isPassed() {
        return status == Status.PASSED;
    }

    public boolean isFailed() {
        return status == Status.FAILED;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
