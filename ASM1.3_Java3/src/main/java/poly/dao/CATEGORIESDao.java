package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import poly.entity.CATEGORIES;
import poly.utils.JdbcHelper;

public class CATEGORIESDao extends WebDao<CATEGORIES, Integer> {
	final String INSERT_SQL = "INSERT INTO CATEGORIES(Id, Name) VALUES(?,?)";
	final String UPDATE_SQL = "UPDATE CATEGORIES SET Name=? WHERE Id=?";
	final String DELETE_SQL = "DELETE FROM CATEGORIES WHERE Id=?";
	final String SELECT_ALL_SQL = "SELECT * FROM CATEGORIES";
	final String SELECT_BY_ID_SQL = "SELECT * FROM CATEGORIES WHERE Id=?";

    

    @Override
    public void insert(CATEGORIES entity) {
        JdbcHelper.update(INSERT_SQL,entity.getId(),entity.getName());
    }

    @Override
    public void update(CATEGORIES entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getName(),entity.getId());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<CATEGORIES> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public CATEGORIES selectByid(Integer id) {
    List<CATEGORIES> list= selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);    
     }

    @Override
    public List<CATEGORIES> selectBySql(String sql, Object... args) {
       List<CATEGORIES> list= new ArrayList<>();
        try {
            ResultSet rs=JdbcHelper.query(sql, args);
            while (rs.next()) {                
            	CATEGORIES entity = new CATEGORIES();
                entity.setId(rs.getString("Id"));
                entity.setName(rs.getString("Name"));

                list.add(entity);
            }
             rs.getStatement().getConnection().close();
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
