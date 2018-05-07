package OnlinemusicstoreClasses;

public class CurrentUser {
    private static CurrentUser currentUser = null;
    private String userName;
    private int userId;
    private boolean isArtist;
    private boolean isAdmin;

    private CurrentUser(){
    }

    public static CurrentUser getInstance(){
        if (currentUser == null){
            currentUser = new CurrentUser();
        }
        return currentUser;
    }


    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isArtist(){
        return isArtist;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setArtist(boolean isArtist){
        this.isArtist = isArtist;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}
