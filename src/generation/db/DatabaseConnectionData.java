package generation.db;

/**
 * Класс содержит информацию необходимую для соединения с БД
 * @author hindu
 *  */
public class DatabaseConnectionData {
    private String connectionString;
    private String user;
    private String password;

    public String getConnectionString() {
	return connectionString;
    }

    public void setConnectionString(String connectionString) {
	this.connectionString = connectionString;
    }

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
