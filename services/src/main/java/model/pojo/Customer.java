package model.pojo;

public class Customer {
    private int id;
    private String name;
    private String login;
    private String password;

    public Customer() {
        id = -1;
    }

    public Customer(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        id = -1;
    }

    public Customer(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer: " +
                "name='" + name + '\'' +
                ", login='" + login + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (!login.equals(customer.login)) return false;
        return password != null ? password.equals(customer.password) : customer.password == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + login.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }
}
