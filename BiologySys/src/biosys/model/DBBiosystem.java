package biosys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DBBiosystem implements Biosystem {
	
	private static final Logger log = Logger.getLogger(DBBiosystem.class);
	
	private static final String SQL_GET_ALL_LIVING = "SELECT * FROM living_entity";
	private static final String SQL_GET_ALL_CLASSES = "SELECT * FROM classification";
	private static final String SQL_GET_LIVING = "SELECT * FROM living_entity WHERE id = ? ";
	private static final String SQL_GET_CLASS = "SELECT * FROM classification WHERE id = ? ";
	private static final String SQL_DELETE_LIVING = "DELETE FROM living_entity WHERE id = ? ";
	private static final String SQL_DELETE_CLASS = "DELETE FROM classification WHERE id = ? ";
	private static final String SQL_UPDATE_LIVING = "UPDATE living_entity " +
			"SET name = ?, name_latin = ?, lifespan = ?, avg_weight = ?, " + 
			"native_range = ?,  population = ?, class = ? WHERE id = ? ";
	private static final String SQL_UPDATE_CLASS = "UPDATE classification " +
			"SET name = ?, parent = ? WHERE id = ? ";
	private static final String SQL_ADD_LIVING = "INSERT INTO living_entity " +
			"VALUES(seq_living_entity.nextval, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_ADD_CLASS = "INSERT INTO classification " +
			"VALUES(seq_classification.nextval, ?, ?)";
	
	
	private Connection conn;

	public DBBiosystem(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Living> getAllLiving() throws SQLException {
		Statement st = null;
		List<Living> list = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_GET_ALL_LIVING);
			list = new ArrayList<Living>();
			while(rs.next()) {
				Living temp = new Living();
				temp.setId(Integer.parseInt(rs.getString("id")));
				temp.setName(rs.getString("name"));
				temp.setNameLatin(rs.getString("name_latin"));
				temp.setLifespan(Integer.parseInt(rs.getString("lifespan")));
				temp.setAvgWeight(Double.parseDouble(rs.getString("avg_weight")));
				temp.setNativeRange(rs.getString("native_range"));
				temp.setPopulation(Long.parseLong(rs.getString("population")));
				temp.setBioClass(Integer.parseInt(rs.getString("class")));
				list.add(temp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			st.close();
		}
		return list;
	}

	@Override
	public Living getLiving(int id) throws SQLException {
		PreparedStatement pst = null;
		Living living = null;
		try {
			pst = conn.prepareStatement(SQL_GET_LIVING);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			living = new Living();
			living.setId(Integer.parseInt(rs.getString("id")));
			living.setName(rs.getString("name"));
			living.setNameLatin(rs.getString("name_latin"));
			living.setLifespan(Integer.parseInt(rs.getString("lifespan")));
			living.setAvgWeight(Double.parseDouble(rs.getString("avg_weight")));
			living.setNativeRange(rs.getString("native_range"));
			living.setPopulation(Long.parseLong(rs.getString("population")));
			living.setBioClass(Integer.parseInt(rs.getString("class")));
		} finally {
			pst.close();
		}
		return living;
	}

	@Override
	public void addLiving(Living living) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL_ADD_LIVING);
			pst.setString(1, living.getName());
			pst.setString(2, living.getNameLatin());
			pst.setInt(3, living.getLifespan());
			pst.setDouble(4, living.getAvgWeight());
			pst.setString(5, living.getNativeRange());
			pst.setLong(6, living.getPopulation());
			pst.setInt(7, living.getBioClass());
			
			log.info("Add living: " + pst.executeUpdate());
		} finally {
			pst.close();
		}	
	}

	@Override
	public void updateLiving(Living living) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL_UPDATE_LIVING);
			pst.setString(1, living.getName());
			pst.setString(2, living.getNameLatin());
			pst.setInt(3, living.getLifespan());
			pst.setDouble(4, living.getAvgWeight());
			pst.setString(5, living.getNativeRange());
			pst.setLong(6, living.getPopulation());
			pst.setInt(7, living.getBioClass());
			pst.setInt(8, living.getId());
				
			log.info("Update living: " + pst.executeUpdate());
		} finally {
			pst.close();
		}
	}

	@Override
	public void removeLiving(int id) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL_DELETE_LIVING);
			pst.setInt(1, id);
			pst.executeUpdate();
		} finally {
			pst.close();
		}	
	}
	
	public List<Living> getAllLivingOrderBy(String colname) throws SQLException {
		Statement st = null;
		List<Living> list = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_GET_ALL_LIVING + " order by " + colname);
			list = new ArrayList<Living>();
			while(rs.next()) {
				Living temp = new Living();
				temp.setId(Integer.parseInt(rs.getString("id")));
				temp.setName(rs.getString("name"));
				temp.setNameLatin(rs.getString("name_latin"));
				temp.setLifespan(Integer.parseInt(rs.getString("lifespan")));
				temp.setAvgWeight(Double.parseDouble(rs.getString("avg_weight")));
				temp.setNativeRange(rs.getString("native_range"));
				temp.setPopulation(Long.parseLong(rs.getString("population")));
				temp.setBioClass(Integer.parseInt(rs.getString("class")));
				list.add(temp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			st.close();
		}
		return list;
	}

	@Override
	public List<BioClass> getAllClasses() throws SQLException {
		Statement st = null;
		List<BioClass> list = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_GET_ALL_CLASSES);
			list = new ArrayList<BioClass>();
			while(rs.next()) {
				BioClass temp = new BioClass();
				temp.setId(Integer.parseInt(rs.getString("id")));
				temp.setName(rs.getString("name"));
				temp.setParentId(rs.getString("parent"));
				list.add(temp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			st.close();
		}
		return list;
	}

	@Override
	public BioClass getBioClass(int id) throws SQLException {
		PreparedStatement pst = null;
		BioClass bioClass = null;
		try {
			pst = conn.prepareStatement(SQL_GET_CLASS);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			bioClass = new BioClass();
			bioClass.setId(Integer.parseInt(rs.getString("id")));
			bioClass.setName(rs.getString("name"));
			bioClass.setParentId(rs.getString("parent"));
		} finally {
			pst.close();
		}
		return bioClass;
	}

	@Override
	public void addBioClass(BioClass bio) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL_ADD_CLASS);
			pst.setString(1, bio.getName());
			pst.setString(2, bio.getParentId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}	
	}

	@Override
	public void updateBioClass(BioClass bio) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL_UPDATE_CLASS);
			pst.setString(1, bio.getName());
			pst.setString(2, bio.getParentId());
			pst.setInt(3, bio.getId());
			pst.executeUpdate();	
		} finally {
			pst.close();
		}
	}

	@Override
	public void removeBioClass(int id) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL_DELETE_CLASS);
			pst.setInt(1, id);
			pst.executeUpdate();
		} finally {
			pst.close();
		}
		
	}
	
	public List<BioClass> getAllClassesOrderBy(String colname) throws SQLException {
		Statement st = null;
		List<BioClass> list = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL_GET_ALL_CLASSES + " order by " + colname);
			list = new ArrayList<BioClass>();
			while(rs.next()) {
				BioClass temp = new BioClass();
				temp.setId(Integer.parseInt(rs.getString("id")));
				temp.setName(rs.getString("name"));
				temp.setParentId(rs.getString("parent"));
				list.add(temp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			st.close();
		}
		return list;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
