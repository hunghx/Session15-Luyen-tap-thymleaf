package ra.model;

public class User {
    private int id;
    private String username;
    private  String password;
    private String fullName;
    private String avatarUrl;
    private boolean status;
    private int roleId;

    public User() {
    }

    public User(int id, String username, String password, String fullName, String avatarUrl, boolean status, int roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.roleId = roleId;
    }

    public User(String username, String password, String fullName, String avatarUrl) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
