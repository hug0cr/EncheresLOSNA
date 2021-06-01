/**
 * 
 */
package fr.eni.encheresLOSNA.dal;

import java.util.List;

/**
 * @author hug0cr
 * @version EncheresLOSNA - V1.0
 * @date 1 juin 2021 - 10:27:23
 */
public interface DAO<T> {
	public void insert(T t) throws DALException;
	public void update(T t) throws DALException;
	public T selectById(int id) throws DALException;
	public List<T> selectAll() throws DALException;
	public void delete(T t) throws DALException;
}
