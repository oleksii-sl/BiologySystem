package biosys.model;

import java.io.Serializable;

public class BioClass implements Serializable {
    
    private String name;
    private String parentId;
    private int id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + id + " " + name + " " + parentId;
    }
}
