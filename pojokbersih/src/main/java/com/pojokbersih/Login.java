package com.pojokbersih;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.pojokbersih.Model.Admin;

public class Login {
    private String password;
    private String username;

    public Login(String usn, String pw_u) {
        username = usn;
        password = pw_u;
    }

    public Boolean authenticate() {
        DB db = new DB();

        String query_admin = "SELECT * FROM admin WHERE username_admin = '" + username + "'";

        List<Object> rs = db.runQuery(query_admin);
        if(rs.isEmpty()) {
            return false;
        }
        else {
            Admin adm = new Admin(rs.get(0));
            if(BCrypt.checkpw(password, adm.getPasswordAdmin())) {
                return true;
            }
            else {
                return false;
            }
        }
    }

}
