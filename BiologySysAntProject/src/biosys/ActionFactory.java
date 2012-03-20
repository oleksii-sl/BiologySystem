package biosys;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	
	Map<String, Class> map;
	
	public ActionFactory() {
		defaultMap();
	}
	
	@SuppressWarnings("unchecked")
	public Action create(String actionName) {
		Class<Action> klass = (Class<Action>) map.get(actionName);
        if (klass == null)
             throw new RuntimeException(getClass() + " was unable to find " +
             	"an action named '" + actionName + "'.");
        
        Action actionInstance = null;
        try {
             actionInstance = (Action) klass.newInstance();
        } catch (Exception e) {
             e.printStackTrace();
        }

        return actionInstance;
	}
	
	private void defaultMap() {
        map = new HashMap<String, Class>();
        
        map.put("addAliveAction", AddAliveAction.class);
        map.put("addClassAction", AddClassAction.class);
        map.put("updateAliveAction", UpdateAliveAction.class);
        map.put("updateClassAction", UpdateClassAction.class);
        map.put("deleteAliveAction", DeleteAliveAction.class);
        map.put("deleteClassAction", DeleteClassAction.class);
   }

}
