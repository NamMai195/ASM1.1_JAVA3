package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import poly.entity.NEWSLETTERS;
import poly.utils.JdbcHelper;

public class NEWSLETTERSDao extends WebDao<NEWSLETTERS, Integer> {
	final String INSERT_SQL = "INSERT INTO NEWSLETTERS(Email, Enabled) VALUES(?,?)";
	final String UPDATE_SQL = "UPDATE NEWSLETTERS SET Enabled=? WHERE Email=?";
	final String DELETE_SQL = "DELETE FROM NEWSLETTERS WHERE Email=?";
	final String SELECT_ALL_SQL = "SELECT * FROM NEWSLETTERS";
	final String SELECT_BY_ID_SQL = "SELECT * FROM NEWSLETTERS WHERE Email=?";

    

    @Override
    public void insert(NEWSLETTERS entity) {
        JdbcHelper.update(INSERT_SQL,entity.getEmail(),entity.getEnabled());
    }

    @Override
    public void update(NEWSLETTERS entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getEnabled(),entity.getEmail());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<NEWSLETTERS> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NEWSLETTERS selectByid(Integer id) {
    List<NEWSLETTERS> list= selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);    
     }

    @Override
    public List<NEWSLETTERS> selectBySql(String sql, Object... args) {
       List<NEWSLETTERS> list= new ArrayList<>();
        try {
            ResultSet rs=JdbcHelper.query(sql, args);
            while (rs.next()) {                
            	NEWSLETTERS entity = new NEWSLETTERS();
                entity.setEmail(rs.getString("Email"));
                entity.setEnabled(rs.getBoolean("Enabled"));

                list.add(entity);
            }
             rs.getStatement().getConnection().close();
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
