import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Apraxin Vladimir on 8.3.18.
 */
public class Task implements Comparable<Task> {
    private String taskName;
    private String sha;
    private LocalDateTime latestChangeCommitTime;
    private LocalDate deadLine;
    private Boolean isPassable;

    Task(String taskName, LocalDate deadLine) {
        this.taskName = taskName;
        this.deadLine = deadLine;
    }

    @Override
    public int compareTo(Task o) {
        return this.taskName.compareTo(o.taskName);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getLatestChangeCommitTime() {
        return latestChangeCommitTime;
    }

    public void setLatestChangeCommitTime(LocalDateTime latestChangeCommitTime) {
        this.latestChangeCommitTime = latestChangeCommitTime;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public Boolean getPassable() {
        return isPassable;
    }

    public void setPassable(Boolean passable) {
        isPassable = passable;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", sha='" + sha + '\'' +
                ", latestChangeCommitTime=" + latestChangeCommitTime +
                ", deadLine=" + deadLine +
                ", isPassable=" + isPassable +
                '}';
    }
}
