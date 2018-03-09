import java.time.LocalDateTime;
import java.util.Date;

public class CommitData {

    private String path;
    private int size;
    private String sha;
    private Long latestChangeCommitTime;
    private boolean isalive;

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    public boolean isIsalive() {

        return isalive;
    }

    public Long getLatestChangeCommitTime() {
        return latestChangeCommitTime;
    }

    public void setLatestChangeCommitTime(Long latestChangeCommitTime) {
        this.latestChangeCommitTime = latestChangeCommitTime;
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }

    public String getSha() {
        return sha;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
