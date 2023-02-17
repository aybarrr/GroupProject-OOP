package Entities;

import java.sql.Connection;
import java.sql.Statement;

public class Admin {
    private String login;
    private String password;
    public Admin(String login, String password) {
            setLogin( login );
            setPassword( password );
        }

        //    Setters and Getters
        public String getPassword() {
            return password;
        }
        public void setPassword( String password ) {
            this.password = password;
        }
        public String getUsername() {
            return login;
        }
        public void setLogin( String login ) {
            this.login = login;
        }

        //    Methods
        public void registerNewAdmin( Connection conn ) {
            Statement statement;

            try {
                String query = String.format( "insert into %s( username, password ) values( '%s', '%s' );", "Admins", this.login,this.password );
                statement = conn.createStatement();
                statement.executeUpdate( query );
                System.out.println( "New admin successfully registered!" );
            } catch ( Exception e ) {
                System.out.println( e );
            }
        }
    }


