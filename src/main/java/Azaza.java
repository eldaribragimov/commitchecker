import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Apraxin Vladimir on 8.3.18.
 */
public class Azaza {

    public static void main(String[] args) throws IOException,JSONException {

        HashMap<String,CommitData> datamap;
        ArrayList<Files> links;
        ArrayList<Files> deadlines;
        FileReader m = new FileReader();
        links = m.readlink("links");
        deadlines = m.readdead("deadlines");
        ArrayList<Task> tasks = new ArrayList<>();
        for (int j=0;j<links.size();j++) {
            String link = links.get(j).getLink();

            //GitHubClient gitHubClient = new GitHubClient();
            //gitHubClient.setCredentials("2pr-commit-checker", "228pr322");

            //RepositoryService service1 = new RepositoryService();
            //Repository tasks = service1.getRepository("2pr-commit-checker", "tasks");

            RepositoryId repositoryId = RepositoryId.createFromUrl(link);

            List<Commit> commitList = new ArrayList<>();

            CommitService commitService = new CommitService();
            List<RepositoryCommit> repositoryCommits = commitService.getCommits(repositoryId);
            for (RepositoryCommit repositoryCommit :
                    repositoryCommits) {
                commitList.add(repositoryCommit.getCommit());
            }

            datamap = new HashMap<>();

            for (Commit commit :
                    commitList) {
                Tree tree = commit.getTree();
                JSONObject json = JsonReader.readJsonFromUrl(tree.getUrl());
                JSONArray treeArray = json.getJSONArray("tree");
                for (int i = 0; i < treeArray.length(); i++) {
                    try {
                        JSONObject file = treeArray.getJSONObject(i);
                        CommitData a = new CommitData();
                        a.setPath(file.getString("path"));
                        a.setSha(file.getString("sha"));
                        a.setSize(file.getInt("size"));
                        a.setLatestChangeCommitTime(commit.getAuthor().getDate().getTime());
                        if (!datamap.containsKey(a.getPath())) {
                            a.setIsalive(true);
                            datamap.put(a.getPath(), a);
                        } else {
                            if (datamap.get(a.getPath()).getSha().equals(a.getSha())) {
                                datamap.remove(a.getPath());
                                datamap.put(a.getPath(), a);
                            }
                        }
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
            for (int v=0;v<deadlines.size();v++){
                if (datamap.containsKey(deadlines.get(v).getName())){
                    Long date = datamap.get(deadlines.get(v).getName()).getLatestChangeCommitTime();
                    Long date1 = deadlines.get(v).getDate();
                    if ((date<date1)&&(datamap.get(deadlines.get(v).getName()).getSize()>0)){
                        Task t = new Task();
                        t.setPassed(true);
                        t.setTaskNumber(Integer.valueOf(deadlines.get(v).getName()));
                        tasks.add(t);
                    }
                    else {
                        Task t = new Task();
                        t.setPassed(false);
                        t.setTaskNumber(Integer.valueOf(deadlines.get(v).getName()));
                        tasks.add(t);
                    }
                }
                else {
                    Task t = new Task();
                    t.setPassed(false);
                    t.setTaskNumber(Integer.valueOf(deadlines.get(v).getName()));
                    tasks.add(t);
                }
            }
        }
        for (Task t:tasks
             ) {
            System.out.println(t.getTaskNumber()+" "+t.getPassed());
        }
    }
}
