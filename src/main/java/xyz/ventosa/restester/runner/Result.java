package xyz.ventosa.restester.runner;

import xyz.ventosa.restester.util.Util;

public abstract class Result {

    protected boolean executed = false;
    protected boolean passed;
    protected double executionTime = 0;
    protected String name;

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed() {
        this.passed = true;
    }

    public void setFailed() {
        this.passed = false;
    }

    public double getExecutionTime() {
        return Util.round(executionTime, 3);
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
}
