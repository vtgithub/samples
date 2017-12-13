package sql.dal.dao;

import sql.dal.entities.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vahid on 3/24/17.
 */
public class DBHelper {

    private static Map<Class, Class> entityDaoMap = new HashMap<Class, Class>();

    static {
        entityDaoMap.put(StudentDAOImpl.class, Student.class);
    }

    public static String getEntityClassName(Class daoClass){
        String clazz = entityDaoMap.get(daoClass).getName();
        return clazz;
    }
}
