package biosys.model;

import java.sql.SQLException;
import java.util.List;

public interface Biosystem {
	
	List<Living> getAllLiving() throws SQLException;
	
	Living getLiving(int id) throws SQLException;
	
	void addLiving(Living living) throws SQLException;
	
	void updateLiving(Living living) throws SQLException;
	
	void removeLiving(int id) throws SQLException;
	
	List<BioClass> getAllClasses() throws SQLException;
	
	BioClass getBioClass(int id) throws SQLException;
	
	void addBioClass(BioClass bio) throws SQLException;
	
	void updateBioClass(BioClass bio) throws SQLException;
	
	void removeBioClass(int id) throws SQLException;
}
