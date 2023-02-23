package Entities;

public class Admin {
    private String name;
    private String password;
    public Admin(String name, String password) {
            setName( name );
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
            return name;
        }

        public void setName( String login ) {
            this.name = login;
        }

        //    Methods

    }


