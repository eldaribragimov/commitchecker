public class Files {

    private String name;
    private String link;
    private Long  date;

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDate() {

        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {

        return name;
    }

    public String getLink() {
        return link;
    }
}
