package id.odojadmin.model;

/**
 * Created by Andini Rachmah on 11/12/18.
 */
public class Admin {
    private String id;
    private String email;
    private String noAdmin;
    private String name;
    private String totalGroup;
    private String origin;
    private String phone;
    private String status;
    private String password;

    public Admin() {
    }

    public Admin(String id, String email, String noAdmin, String name, String totalGroup, String origin, String phone, String status, String password) {
        this.id = id;
        this.email = email;
        this.noAdmin = noAdmin;
        this.name = name;
        this.totalGroup = totalGroup;
        this.origin = origin;
        this.phone = phone;
        this.status = status;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoAdmin() {
        return noAdmin;
    }

    public void setNoAdmin(String noAdmin) {
        this.noAdmin = noAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalGroup() {
        return totalGroup;
    }

    public void setTotalGroup(String totalGroup) {
        this.totalGroup = totalGroup;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
