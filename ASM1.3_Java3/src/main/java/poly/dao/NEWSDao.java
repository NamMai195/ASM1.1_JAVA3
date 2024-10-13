package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import poly.entity.NEWS;
import poly.utils.JdbcHelper;

public class NEWSDao extends WebDao<NEWS, String>{
	final String INSERT_SQL = "INSERT INTO NEWS(Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES(?,?,?,?,?,?,?,?,?)";
	final String UPDATE_SQL = "UPDATE NEWS SET Title=?, Content=?, Image=?, PostedDate=?, Author=?, ViewCount=?, CategoryId=?, Home=? WHERE Id=?";
	final String DELETE_SQL = "DELETE FROM NEWS WHERE Id=?";
	final String SELECT_ALL_SQL = "SELECT * FROM NEWS";
	final String SELECT_BY_ID_SQL = "SELECT * FROM NEWS WHERE Id=?";

    

    @Override
    public void insert(NEWS entity) {
        JdbcHelper.update(INSERT_SQL,entity.getId(),entity.getTitle(),entity.getContent(),entity.getImage(),entity.getPostedDate(),entity.getAuthor(),entity.getViewCount(),entity.getCategoryId(),entity.getHome());
    }

    @Override
    public void update(NEWS entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getTitle(),entity.getContent(),entity.getImage(),entity.getPostedDate(),entity.getAuthor(),entity.getViewCount(),entity.getCategoryId(),entity.getHome(),entity.getId());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<NEWS> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NEWS selectByid(String id) {
    List<NEWS> list= selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);    }

    @Override
    public List<NEWS> selectBySql(String sql, Object... args) {
       List<NEWS> list= new ArrayList<>();
        try {
            ResultSet rs=JdbcHelper.query(sql, args);
            while (rs.next()) {                
                NEWS entity = new NEWS();
                entity.setId(rs.getString("Id"));
                entity.setTitle(rs.getString("Title"));
                entity.setContent(rs.getString("Content"));
                entity.setImage(rs.getString("Image"));
                entity.setPostedDate(rs.getTimestamp("PostedDate"));
                entity.setAuthor(rs.getString("Author"));
                entity.setViewCount(rs.getInt("ViewCount"));
                entity.setCategoryId(rs.getString("CategoryId"));
                entity.setHome(rs.getBoolean("Home"));

                list.add(entity);
            }
             rs.getStatement().getConnection().close();
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
