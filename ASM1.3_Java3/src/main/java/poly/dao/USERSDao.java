package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import poly.entity.USERS;
import poly.utils.JdbcHelper;

public class USERSDao extends WebDao<USERS, Integer>{
	final String INSERT_SQL = "INSERT INTO USERS(Id, Password, Fullname, Birthday, Gender, Moble, Email, Role) VALUES(?,?,?,?,?,?,?,?)";
	final String UPDATE_SQL = "UPDATE USERS SET Password=?, Fullname=?, Birthday=?, Gender=?, Moble=?, Email=?, Role=? WHERE Id=?";
	final String DELETE_SQL = "DELETE FROM USERS WHERE Id=?";
	final String SELECT_ALL_SQL = "SELECT * FROM USERS";
	final String SELECT_BY_ID_SQL = "SELECT * FROM USERS WHERE Id=?";

    

    @Override
    public void insert(USERS entity) {
        JdbcHelper.update(INSERT_SQL,entity.getId(),entity.getPassword(),entity.getFullname(),entity.getBirthday(),entity.getGender(),entity.getMoble(),entity.getEmail(),entity.getRole());
    }

    @Override
    public void update(USERS entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getPassword(),entity.getFullname(),entity.getBirthday(),entity.getGender(),entity.getMoble(),entity.getEmail(),entity.getRole(),entity.getId());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<USERS> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public USERS selectByid(Integer id) {
    List<USERS> list= selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);    }

    @Override
    public List<USERS> selectBySql(String sql, Object... args) {
       List<USERS> list= new ArrayList<>();
        try {
            ResultSet rs=JdbcHelper.query(sql, args);
            while (rs.next()) {                
            	USERS entity = new USERS();
                entity.setId(rs.getString("Id"));
                entity.setPassword(rs.getString("Password"));
                entity.setFullname(rs.getString("Fullname"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setMoble(rs.getString("Moble"));
                entity.setEmail(rs.getString("Email"));
                entity.setRole(rs.getBoolean("Role"));

                list.add(entity);
            }
             rs.getStatement().getConnection().close();
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
