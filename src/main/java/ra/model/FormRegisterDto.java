package ra.model;

import org.springframework.web.multipart.MultipartFile;

public class FormRegisterDto {
    private String username;
    private String fullName;
    private String password;
    private MultipartFile avatar;

    public FormRegisterDto(String username, String fullName, String password, MultipartFile avatar) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
