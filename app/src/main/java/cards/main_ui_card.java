package cards;

public class main_ui_card {

    private String Title;

    private int Thumbnail;

    public main_ui_card() {

    }

    public main_ui_card(String title, int thumbnail) {

        Title = title;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }


    public int getThumbnail() {
        return Thumbnail;
    }

}
