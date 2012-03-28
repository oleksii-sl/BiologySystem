package biosys.model;

import java.sql.SQLException;
import java.util.List;

public interface BiosystemDAO {
    
    List<Alive> getAllAlive() throws SQLException;
    
    Alive getAlive(int id) throws SQLException;
    
    void addAlive(Alive Alive) throws SQLException;
    
    void updateAlive(Alive Alive) throws SQLException;
    
    void removeAlive(int id) throws SQLException;
    
    List<Alive> getAllAliveConstraint(String ordercol, List<String> constraints) throws SQLException;
    
    List<BioClass> getAllClasses() throws SQLException;
    
    BioClass getBioClass(int id) throws SQLException;
    
    void addBioClass(BioClass bio) throws SQLException;
    
    void updateBioClass(BioClass bio) throws SQLException;
    
    void removeBioClass(int id) throws SQLException;
    
    List<BioClass> getAllClassesConstraint(String ordercol, List<String> constraints) throws SQLException;
    
    List<BioClass> getClassesHierarchy(int id) throws SQLException;
    
    List<BioClass> getAllClassesHierarchy() throws SQLException;
    
}
