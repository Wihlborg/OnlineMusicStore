package OnlinemusicstoreClasses;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String securityAnswer;
    private int admin;
    private int artist;

    public User(int userId, String userName, String email, String securityAnswer, int isAdmin, int isArtist) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.securityAnswer = securityAnswer;
        this.admin = isAdmin;
        this.artist = isArtist;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public int getAdmin() {
        return admin;
    }

    public int getArtist() {
        return artist;
    }
}
