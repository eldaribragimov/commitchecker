import java.time.LocalDateTime;

/**
 * Created by Apraxin Vladimir on 8.3.18.
 */
public class Task {
    private Integer taskNumber;
    private String sha;
    private LocalDateTime latestChangeCommitTime;
    private LocalDateTime deadLine;
    private Boolean isPassed;

    public Task() {
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public LocalDateTime getLatestChangeCommitTime() {
        return latestChangeCommitTime;
    }

    public void setLatestChangeCommitTime(LocalDateTime latestChangeCommitTime) {
        this.latestChangeCommitTime = latestChangeCommitTime;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public Boolean getPassed() {
        return isPassed;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }
}
