package database.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class SistemaDAO {
	
	public abstract List<Object> Select() throws SQLException;
	public abstract int Insert(Object param) throws SQLException;
	public abstract long Delete(Object param) throws SQLException;
	public abstract long Update(Object param) throws SQLException;

}
