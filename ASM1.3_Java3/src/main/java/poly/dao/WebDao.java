package poly.dao;

import java.util.List;

import poly.entity.NEWS;


public abstract class WebDao<Enti, Key> {
    public abstract void insert(Enti entity);
    public abstract void update(Enti entity);
    public abstract void delete(Key id);
    public abstract List<Enti> selectAll();
    public abstract Enti selectByid(Key id);
    public abstract List<Enti> selectBySql(String sql, Object... args);
}
