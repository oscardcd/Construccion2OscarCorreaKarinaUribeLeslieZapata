package app.domain.models;

public class EmergencyContact {

    private String name;
    private String related;
    private String phone;

    // Constructor
    public EmergencyContact(String name, String related, String phone) {
        this.name = name;
        this.related = related;
        this.phone = phone;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelate() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
