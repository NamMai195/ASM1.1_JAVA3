package poly.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import poly.entity.USERS;
import poly.utils.JdbcHelper;

public class USERSDao extends WebDao<USERS, String> {
    final String INSERT_SQL = "INSERT INTO USERS(Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE USERS SET Password=?, Fullname=?, Birthday=?, Gender=?, Mobile=?, Email=?, Role=? WHERE Id=?";
    final String DELETE_SQL = "DELETE FROM USERS WHERE Id=?";
    final String SELECT_ALL_SQL = "SELECT * FROM USERS";
    final String SELECT_BY_ID_SQL = "SELECT * FROM USERS WHERE Id=?";
    final String CHECK_LOGIN_SQL = "SELECT * FROM USERS WHERE Id = ? AND Password = ?";

    @Override
    public void insert(USERS entity) {
        JdbcHelper.update(INSERT_SQL, entity.getId(), entity.getPassword(), entity.getFullname(), 
                new java.sql.Date(entity.getBirthday().getTime()), entity.getGender(), 
                entity.getMobile(), entity.getEmail(), entity.getRole());
    }

    @Override
    public void update(USERS entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getPassword(), entity.getFullname(), 
                new java.sql.Date(entity.getBirthday().getTime()), entity.getGender(), 
                entity.getMobile(), entity.getEmail(), entity.getRole(), 
                entity.getId());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<USERS> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public USERS selectByid(String id) {
        List<USERS> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<USERS> selectBySql(String sql, Object... args) {
        List<USERS> list = new ArrayList<>();
        try (ResultSet rs = JdbcHelper.query(sql, args)) {
            while (rs.next()) {
                USERS entity = new USERS();
                entity.setId(rs.getString("Id"));
                entity.setPassword(rs.getString("Password"));
                entity.setFullname(rs.getString("Fullname"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setMobile(rs.getString("Mobile"));
                entity.setEmail(rs.getString("Email"));
                entity.setRole(rs.getBoolean("Role"));
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return list;
    }

    // Phương thức kiểm tra đăng nhập
    public USERS checkLogin(String username, String password) {
        List<USERS> list = selectBySql(CHECK_LOGIN_SQL, username, password);
        return list.isEmpty() ? null : list.get(0);
    }
}
