import java.util.Set;

/**
 * Created by Apraxin Vladimir on 9.3.18.
 */
public class User {
    private String username;
    private String linkToRepository;
    private Set<Task> tasks;
    private Integer grade = 0;

    User(String username, String linkToRepository) {
        this.username = username;
        this.linkToRepository = linkToRepository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLinkToRepository() {
        return linkToRepository;
    }

    public void setLinkToRepository(String linkToRepository) {
        this.linkToRepository = linkToRepository;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Ученик: " + username + "\n" +
                "Адрес репозитория: " + linkToRepository + "\n" +
                "Итоговая оценка: " + grade + " баллов" + "\n";
    }
}

