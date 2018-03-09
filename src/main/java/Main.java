import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.Tree;
import org.eclipse.egit.github.core.service.CommitService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Apraxin Vladimir on 9.3.18.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        List<User> users = new ArrayList<>();
        Map<String, LocalDate> deadlines = new TreeMap<>();

        List<String> usersFile = readTwoSubstrings("links");
        List<String> deadlinesFile = readTwoSubstrings("deadlines");

        for (String s :
                deadlinesFile) {
            String[] strings = s.split(",", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(strings[1], formatter);
            deadlines.put(strings[0], localDate);
        }

        for (String s :
                usersFile) {
            String[] strings = s.split(",", 2);
            User user = new User(strings[0], strings[1]);
            Set<Task> taskSet = new TreeSet<>();
            for (Map.Entry<String, LocalDate> entry :
                    deadlines.entrySet()) {
                Task task = new Task(entry.getKey(), entry.getValue());
                taskSet.add(task);
            }
            user.setTasks(taskSet);
            users.add(user);
        }





        for (User user :
                users) {
            RepositoryId repositoryId = RepositoryId.createFromUrl(user.getLinkToRepository());
            List<Commit> commitList = new ArrayList<>();

            CommitService commitService = new CommitService();
            List<RepositoryCommit> repositoryCommits = commitService.getCommits(repositoryId);
            for (RepositoryCommit repositoryCommit :
                    repositoryCommits) {
                commitList.add(repositoryCommit.getCommit());
            }

            Set<Task> taskSet = user.getTasks();

            boolean isLastCommit = true;

            for (Commit commit :
                    commitList) {
                Tree tree = commit.getTree();
                JSONObject jsonObject = JsonReader.readJsonFromUrl(tree.getUrl());
                JSONArray treeArray = jsonObject.getJSONArray("tree");
                for (int i = 0; i < treeArray.length(); i++) {
                    JSONObject file = treeArray.getJSONObject(i);

                    String taskName = file.getString("path");

                    Integer size = file.getInt("size");

                    String sha = file.getString("sha");

                    Date legacyDate = commit.getAuthor().getDate();
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());

                    Boolean isPassable = (size > 0) && isLastCommit;

                    for (Task task :
                            taskSet) {
                        if (task.getTaskName().equals(taskName)) {
                            if (task.getLatestChangeCommitTime() == null) {
                                task.setSha(sha);
                                task.setLatestChangeCommitTime(localDateTime);
                                task.setPassable(isPassable);
                            } else if (task.getSha().equals(sha)) {
                                task.setLatestChangeCommitTime(localDateTime);
                            }
                        }
                    }
                }
                isLastCommit = false;
            }

            for (Task task :
                    taskSet) {
                if (task.getPassable() != null && task.getPassable()) {
                    if (task.getLatestChangeCommitTime().isBefore(task.getDeadLine().atStartOfDay())) {
                        user.setGrade(user.getGrade() + 1);
                    }
                }
            }
        }
        System.out.print("Количество заданий: ");
        System.out.println(deadlines.size());
        System.out.println();
        for (User user :
                users) {
            System.out.println(user);
        }
    }

    private static List<String> readTwoSubstrings(String pathToFile) throws IOException {
        List<String> lines;
        Stream<String> stream = Files.lines(Paths.get(pathToFile));
        lines = stream.collect(Collectors.toList());
        return lines;
    }
}
