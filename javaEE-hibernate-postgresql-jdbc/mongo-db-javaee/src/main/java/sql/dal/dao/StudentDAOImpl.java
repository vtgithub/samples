package sql.dal.dao;

import sql.dal.entities.Student;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

/**
 * Created by vahid on 3/24/17.
 */
@Stateless
@Transactional
public class StudentDAOImpl extends DAOImpl<Student> implements StudentDAO {
}
