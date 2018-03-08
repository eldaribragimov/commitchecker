import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apraxin Vladimir on 8.3.18.
 */
public class Azaza {

    public static void main(String[] args) throws IOException {

        GitHubClient gitHubClient = new GitHubClient();
        gitHubClient.setCredentials("2pr-commit-checker", "228pr322");

        RepositoryService service1 = new RepositoryService();
        Repository tasks = service1.getRepository("2pr-commit-checker", "tasks");
        System.out.println(tasks.getName());

        RepositoryId repositoryId = RepositoryId.createFromUrl("https://github.com/2pr-commit-checker/tasks");
        System.out.println(repositoryId);

        List<Commit> commitList = new ArrayList<Commit>();

        CommitService commitService = new CommitService();
        List<RepositoryCommit> repositoryCommits = commitService.getCommits(repositoryId);
        for (RepositoryCommit repositoryCommit :
                repositoryCommits) {
            commitList.add(repositoryCommit.getCommit());
        }

        for (Commit commit :
                commitList) {
            Tree tree = commit.getTree();
            System.out.println(tree.getSha());
            JSONObject json = JsonReader.readJsonFromUrl(tree.getUrl());
            JSONArray treeArray = json.getJSONArray("tree");
            for (int i = 0; i < treeArray.length(); i++) {
                JSONObject file = treeArray.getJSONObject(i);
/*
                JSONArray fileArray = file.getJSONArray("" + i);
*/

                System.out.println(file.get("path").toString() + file.get("sha").toString());
            }
            /*JSONObject json10 = json.getJSONArray("tree");*/
        }

    }
}
