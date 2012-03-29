package biosys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBBiosystem implements BiosystemDAO {
    
    private static final Logger log = Logger.getLogger(DBBiosystem.class);
    
    private static final String SQL_GET_ALL_ALIVE = "SELECT * FROM living_entity";
    private static final String SQL_GET_ALL_CLASSES = "SELECT * FROM classification";
    private static final String SQL_GET_ALIVE = "SELECT * FROM living_entity WHERE id = ? ";
    private static final String SQL_GET_CLASS = "SELECT * FROM classification WHERE id = ? ";
    private static final String SQL_DELETE_ALIVE = "DELETE FROM living_entity WHERE id = ? ";
    private static final String SQL_DELETE_CLASS = "DELETE FROM classification WHERE id = ? ";
    
    private static final String SQL_UPDATE_ALIVE = "UPDATE living_entity " +
                            "SET name = ?, name_latin = ?, lifespan = ?, avg_weight = ?, " + 
                            "native_range = ?,  population = ?, class = ? WHERE id = ? ";
    
    private static final String SQL_UPDATE_CLASS = "UPDATE classification " +
                            "SET name = ?, parent = ? WHERE id = ? ";
    
    private static final String SQL_ADD_ALIVE = "INSERT INTO living_entity " +
                            "VALUES(seq_living_entity.nextval, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_ADD_CLASS = "INSERT INTO classification " +
                            "VALUES(seq_classification.nextval, ?, ?)";
    
    private static final String SQL_CLASSES_HIERARCHY_BY_ID = "SELECT * FROM classification " +
                                                    "START WITH id = ? " +
                                                    "CONNECT BY PRIOR id = parent";
    
    private static final String SQL_CLASSES_HIERARCHY = "SELECT * FROM classification " +
                                                    "START WITH parent IS NULL " +
                                                    "CONNECT BY PRIOR id = parent";
    
    private static final String SQL_CHECK_CHILDREN = "SELECT class FROM living_entity " +
                                        "WHERE class = ?";
    
    private static final String REG_EXP_SUBSTRING = "substr: (\\w|-|_)+ (\\w|-|_)+";
    
    private static final String REG_EXP_BETWEEN = "between: (\\w|-|_)+ \\d+(.\\d+)? \\d+(.\\d+)?";
    
    private static final String EXCEPTION_PARENT_CLASS_DELETE = "You are trying to delete a class " +
                                                    "that is referenced in the living entity table";
    
    private DataSource ds;

    public DBBiosystem(DataSource ds) {
        this.ds = ds;
    }
    
    @Override
    public List<Alive> getAllAlive() throws SQLException {
        Connection conn = ds.getConnection();
        Statement st = null;
        List<Alive> list = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL_GET_ALL_ALIVE);
            list = handleAliveResultSet(rs);
        } finally {
            closeAll(conn, st, rs);
        }
        return list;
    }

    @Override
    public Alive getAlive(int id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        Alive alive = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(SQL_GET_ALIVE);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            alive = new Alive();
            alive.setId(Integer.parseInt(rs.getString("id")));
            alive.setName(rs.getString("name"));
            alive.setNameLatin(rs.getString("name_latin"));
            alive.setLifespan(Integer.parseInt(rs.getString("lifespan")));
            alive.setAvgWeight(Double.parseDouble(rs.getString("avg_weight")));
            alive.setNativeRange(rs.getString("native_range"));
            alive.setPopulation(Long.parseLong(rs.getString("population")));
            alive.setBioClass(Integer.parseInt(rs.getString("class")));
        } finally {
            closeAll(conn, pst, rs);
        }
        return alive;
    }

    @Override
    public void addAlive(Alive alive) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(SQL_ADD_ALIVE);
            pst.setString(1, alive.getName());
            pst.setString(2, alive.getNameLatin());
            pst.setInt(3, alive.getLifespan());
            pst.setDouble(4, alive.getAvgWeight());
            pst.setString(5, alive.getNativeRange());
            pst.setLong(6, alive.getPopulation());
            pst.setInt(7, alive.getBioClass());
            
            log.info("Add Alive: " + pst.executeUpdate());
        } finally {
            closeAll(conn, pst, null);
        }    
    }

    @Override
    public void updateAlive(Alive alive) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(SQL_UPDATE_ALIVE);
            pst.setString(1, alive.getName());
            pst.setString(2, alive.getNameLatin());
            pst.setInt(3, alive.getLifespan());
            pst.setDouble(4, alive.getAvgWeight());
            pst.setString(5, alive.getNativeRange());
            pst.setLong(6, alive.getPopulation());
            pst.setInt(7, alive.getBioClass());
            pst.setInt(8, alive.getId());
                
            log.info("Update Alive: " + pst.executeUpdate());
        } finally {
            closeAll(conn, pst, null);
        }
    }

    @Override
    public void removeAlive(int id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(SQL_DELETE_ALIVE);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            closeAll(conn, pst, null);
        }    
    }
    
    public List<Alive> getAllAliveConstraint(String ordercol, List<String> constraints) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        List<Alive> list = null;
        ResultSet rs = null;
        String temp;
        try {
            pst = conn.prepareStatement(temp = SQL_GET_ALL_ALIVE + makeConstraint(ordercol, constraints));
            log.info("getAllAlive statement: " + temp);
            rs = pst.executeQuery();
            list = handleAliveResultSet(rs);
        } finally {
            closeAll(conn, pst, rs);
        }
        return list;
    }

    @Override
    public List<BioClass> getAllClasses() throws SQLException {
        Connection conn = ds.getConnection();
        Statement st = null;
        List<BioClass> list = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL_GET_ALL_CLASSES);
            list = handleClassesResultSet(rs);
        } finally {
            closeAll(conn, st, rs);
        }
        return list;
    }

    @Override
    public BioClass getBioClass(int id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        BioClass bioClass = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(SQL_GET_CLASS);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            bioClass = new BioClass();
            bioClass.setId(Integer.parseInt(rs.getString("id")));
            bioClass.setName(rs.getString("name"));
            bioClass.setParentId(rs.getString("parent"));
        } finally {
            closeAll(conn, pst, rs);
        }
        return bioClass;
    }

    @Override
    public void addBioClass(BioClass bio) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(SQL_ADD_CLASS);
            pst.setString(1, bio.getName());
            pst.setString(2, bio.getParentId());
            pst.executeUpdate();
        } finally {
            closeAll(conn, pst, null);
        }    
    }

    @Override
    public void updateBioClass(BioClass bio) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(SQL_UPDATE_CLASS);
            pst.setString(1, bio.getName());
            pst.setString(2, bio.getParentId());
            pst.setInt(3, bio.getId());
            pst.executeUpdate();    
        } finally {
            closeAll(conn, pst, null);
        }
    }

    @Override
    public void removeBioClass(int id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        PreparedStatement checkSt = null;
        ResultSet checkRs = null;
        try {
            checkSt = conn.prepareStatement(SQL_CHECK_CHILDREN);
            checkSt.setInt(1, id);
            checkRs = checkSt.executeQuery();
            if (checkRs.next())
                throw new ParentClassDeleteException(EXCEPTION_PARENT_CLASS_DELETE);
            pst = conn.prepareStatement(SQL_DELETE_CLASS);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            closeAll(conn, pst, checkRs);
            if (checkSt != null)
                checkSt.close();
        }
        
    }
    
    @Override
    public List<BioClass> getAllClassesConstraint(String ordercol, List<String> constraints)
            throws SQLException {
        
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        List<BioClass> list = null;
        ResultSet rs = null;
        String temp;
        try {
            pst = conn.prepareStatement(temp = SQL_GET_ALL_CLASSES + 
                    makeConstraint(ordercol, constraints));
            log.info("getAllClasses statement: " + temp);
            rs = pst.executeQuery();
            list = handleClassesResultSet(rs);
        } finally {
            closeAll(conn, pst, rs);
        }
        return list;
    }
    
    @Override
    public List<BioClass> getClassesHierarchy(int id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = null;
        List<BioClass> list = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(SQL_CLASSES_HIERARCHY_BY_ID);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            list = handleClassesResultSet(rs);
        } finally {
            closeAll(conn, pst, rs);
        }
        return list;
    }

    @Override
    public List<BioClass> getAllClassesHierarchy() throws SQLException {
        Connection conn = ds.getConnection();
        Statement st = null;
        List<BioClass> list = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL_CLASSES_HIERARCHY);
            list = handleClassesResultSet(rs);
        } finally {
            closeAll(conn, st, rs);
        }
        return list;
    }
    
    private String makeConstraint(String ordercol, List<String> constraints) {
        StringBuilder constr = new StringBuilder();
        boolean first = true;
        if (constraints != null && !constraints.isEmpty()) {
            constr.append(" WHERE ");
            for (String s : constraints) {
                if (s.matches(REG_EXP_SUBSTRING)) {
                    if (!first)
                        constr.append(" AND ");
                    constr.append(sqlSubstringExpression(s));
                } else if (s.matches(REG_EXP_BETWEEN)) {
                    if (!first)
                        constr.append(" AND ");
                    constr.append(sqlBetweenExpression(s));
                } else {
                    throw new WrongInputDataException("Input constraints was wrong!");
                }
                first = false;
            }
        }
        if (ordercol != null)
            constr.append(" ORDER BY ").append(ordercol);
        
        return constr.toString();
    }
    
    private String sqlSubstringExpression(String expression) {
        StringBuilder builder = new StringBuilder(" instr(");
        String[] split = expression.split("\\s+");
        builder.append("lower(").append(split[1]).append("), ");
        builder.append("lower('").append(split[2]).append("')) > 0 ");
        
        return builder.toString();
    }
    
    private String sqlBetweenExpression(String expression) {
        StringBuilder builder = new StringBuilder(" ");
        String[] split = expression.split("\\s+");
        builder.append(split[1]).append(" BETWEEN ").append(split[2]);
        builder.append(" AND ").append(split[3]);
        
        return builder.toString();
    }
    
    private List<Alive> handleAliveResultSet(ResultSet rs) throws NumberFormatException, SQLException {
        List<Alive> list = new ArrayList<Alive>();
        while(rs.next()) {
            Alive temp = new Alive();
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
        return list;
    }
    
    private List<BioClass> handleClassesResultSet(ResultSet rs) throws NumberFormatException, SQLException {
        List<BioClass> list = new ArrayList<BioClass>();
        while(rs.next()) {
            BioClass temp = new BioClass();
            temp.setId(Integer.parseInt(rs.getString("id")));
            temp.setName(rs.getString("name"));
            temp.setParentId(rs.getString("parent"));
            list.add(temp);
        }
        return list;
    }
    
    private void closeAll(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if (conn != null)
            conn.close();
        if (st != null)
            st.close();
        if (rs != null)
            rs.close();
    }
}
